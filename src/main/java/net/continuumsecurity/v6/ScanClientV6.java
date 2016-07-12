package net.continuumsecurity.v6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

import net.continuumsecurity.PolicyNotFoundException;
import net.continuumsecurity.ScanClient;
import net.continuumsecurity.ScanNotFoundException;
import net.continuumsecurity.v6.model.CreateScanRequest;
import net.continuumsecurity.v6.model.ExportFormat;
import net.continuumsecurity.v6.model.ExportScanRequest;
import net.continuumsecurity.v6.model.ExportV6;
import net.continuumsecurity.v6.model.Policies;
import net.continuumsecurity.v6.model.PolicyV6;
import net.continuumsecurity.v6.model.ScanResponseWrapper;
import net.continuumsecurity.v6.model.ScanV6;
import net.continuumsecurity.v6.model.ScansV6;
import net.continuumsecurity.v6.model.Settings;

/**
 * Created by stephen on 07/02/15.
 */
public class ScanClientV6 extends SessionClientV6 implements ScanClient {
	public ScanClientV6(String nessusUrl, boolean acceptAllHostNames) {
		super(nessusUrl, acceptAllHostNames);
	}

	public String getScanStatus(String id) throws ScanNotFoundException {
		WebTarget scanTarget = target.path("/scans");
		int scanId = Integer.parseInt(id);
		ScansV6 scans = getRequest(scanTarget, ScansV6.class);
		for(ScanV6 scan : scans.getScans()){
			if(scanId == scan.getId()){
				return scan.getStatus();
			}
		}
		throw new ScanNotFoundException("No scan with Id: " + id);
	}

	public JsonObject getScanDetails(String id) throws ScanNotFoundException {
        int scanId = Integer.parseInt(id);
		WebTarget scanTarget = target.path("/scans/" + scanId);
        String response = getRequest(scanTarget, String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(response));
        return jsonReader.readObject();
	}

	private PolicyV6 getPolicyV6ByName(String name) {
		WebTarget scanTarget = target.path("/policies");
		Policies reply = getRequest(scanTarget, Policies.class);
		for(PolicyV6 policy : reply.getPolicies()){
			if(name.equalsIgnoreCase(policy.getName()))
				return policy;
		}
		throw new PolicyNotFoundException("No policy with name: " + name);
	}

	public int getScanIDFromName(String name) throws ScanNotFoundException {
		for (ScanV6 scan : listScans().getScans()) {
			if(name.equalsIgnoreCase(scan.getName()))
				return scan.getId();
		}
		throw new ScanNotFoundException("No scan with name " + name);
	}

	public int getPolicyIDFromName(String name) throws PolicyNotFoundException {
		return getPolicyV6ByName(name).getId();
	}

	public String getPolicyUUIDFromName(String name) throws PolicyNotFoundException {
		return getPolicyV6ByName(name).getUuid();
	}

	public String newScan(String scanName, String policyName, String targets) {
		PolicyV6 policy = getPolicyV6ByName(policyName);
		WebTarget scanTarget = target.path("scans");
		CreateScanRequest scanCommand = new CreateScanRequest();
		scanCommand.setUuid(policy.getUuid());
		Settings settings = new Settings();
		settings.setName(scanName);
		settings.setPolicy_id(policy.getId());
		settings.setText_targets(targets);
		scanCommand.setSettings(settings);
		//String response = postRequest(scanTarget,scanCommand,String.class);
		ScanResponseWrapper response = postRequest(scanTarget, scanCommand, ScanResponseWrapper.class);
		if(response.getScan() == null || response.getScan().getId() <= 0)
			throw new RuntimeException("Error creating scan: " + response.getError());
		launchScan(response.getScan().getId());
		return Integer.toString(response.getScan().getId()); //Nessus v5 uses the UUID instead of scanId
	}

	public ScansV6 listScans() {
		WebTarget scanTarget = target.path("scans");
		return getRequest(scanTarget, ScansV6.class);
	}

	public ExportV6 export(int scanId, ExportFormat exportFormat) {
		WebTarget target = this.target.path("scans").path(Integer.toString(scanId)).path("export");
		ExportScanRequest exportScanRequest = new ExportScanRequest();
		exportScanRequest.setFormat(exportFormat.getValue());
		return postRequest(target, exportScanRequest, ExportV6.class);
	}

	public File download(int scanId, ExportV6 export, Path outputPath) throws IOException {
		WebTarget target = this.target.path("scans").path(Integer.toString(scanId)).path("export").path(export.getFile()).path("download");
		Response response = getRequest(target, Response.class, MediaType.APPLICATION_OCTET_STREAM_TYPE);
		String fileName = extractFileName(response);
		InputStream inputStream = (InputStream) response.getEntity();
		File targetFile = Files.createFile(outputPath.resolve(Paths.get(fileName))).toFile();
		writeDownloadedFile(inputStream, targetFile);
		return targetFile;
	}
	public File download(int scanId, ExportFormat exportFormat, Path outputPath) throws IOException {
		return download(scanId, export(scanId, exportFormat), outputPath);
	}

	public void launchScan(int id) {
		WebTarget scanTarget = target.path("scans").path(Integer.toString(id)).path("launch");
		Response response = postRequest(scanTarget, "", Response.class);
		if(response.getStatus() != 200)
			throw new RuntimeException("Error launching scan with ID: " + id + ": " + response.getStatusInfo().getReasonPhrase());
	}

	public boolean isScanRunning(String scanId) {
		try{
			if("completed".equalsIgnoreCase(getScanStatus(scanId)))
				return false;
			if("running".equalsIgnoreCase(getScanStatus(scanId)) || "paused".equalsIgnoreCase(getScanStatus(scanId)))
				return true;
		}catch(ScanNotFoundException e){
			return false;
		}
		return false;
	}

	private void writeDownloadedFile(InputStream inputStream, File targetFile) throws IOException {
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(targetFile);
			byte[] buffer = new byte[8 * 1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} finally {
			if (outStream != null) {
				outStream.close();
			}
		}
	}

	private String extractFileName(Response response) {
		String contentDisposition = response.getHeaderString(HttpHeaders.CONTENT_DISPOSITION);
		return contentDisposition.substring(contentDisposition.indexOf("filename=") + "filename=\\".length(), contentDisposition.length() - 1);
	}
}
