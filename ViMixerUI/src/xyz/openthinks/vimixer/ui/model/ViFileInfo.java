package xyz.openthinks.vimixer.ui.model;

public class ViFileInfo {
	private Long startTime;
	private Long endTime;
	private Long fileLength;
	private Long blockLength;
	
	
	public ViFileInfo() {
		super();
		this.startTime =0L;
		this.endTime = 0L;
		this.fileLength = 0L;
		this.blockLength = 0L;
	}


	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}


	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}


	public void setFileLength(Long fileLength) {
		this.fileLength = fileLength;
	}


	public void setBlockLength(Long blockLength) {
		this.blockLength = blockLength;
	}


	public Long getStartTime() {
		return startTime;
	}


	public Long getEndTime() {
		return endTime;
	}


	public Long getFileLength() {
		return fileLength;
	}


	public Long getBlockLength() {
		return blockLength;
	}


	@Override
	public String toString() {
		StringBuffer buffer =new StringBuffer();
		if(fileLength!=null && fileLength>0){
			buffer.append("File length:"+fileLength);
		}
		if(blockLength!=null && blockLength>0){
			buffer.append(";Block count:"+blockLength);
		}
		if(startTime!=null && startTime>0){
			buffer.append(";Start time:"+startTime);
		}
		if(endTime!=null && endTime>0){
			buffer.append(";Complete time:"+endTime);
		}
		
		return buffer.toString();
	}
	
	
}