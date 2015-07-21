package xyz.openthinks.crypto.mix;

/**
 * The block in {@link MixTarget} need to do mixing. Wrapper {@link Segment}
 * 
 * @author minjdai
 *
 */
public abstract class MixBlock {
	protected Segment segment;
	protected MixBlockStatus status;

	public MixBlock(Segment segment, MixBlockStatus status) {
		this.segment = segment;
		this.status = status;
	}

	public MixBlock(Segment segment) {
		this(segment, MixBlockStatus.NOT_PROCESSED);
	}

	@Override
	public String toString() {
		return "MixBlock [segment=" + segment + "]";
	}
	
	public void markProcessed(){
		setStatus(MixBlockStatus.PROCESSED);
	}
	
	/**
	 * reference its owner {@link MixTarget}
	 * 
	 * @return
	 */
	protected abstract MixTarget target();

	/**
	 * @return The byte array of block content
	 */
	public abstract byte[] getBytes();

	/**
	 * save the changes of current block into its owner
	 */
	public abstract void persist();

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public long getPosition() {
		if (segment != null) {
			return segment.getPosition();
		}
		return 0;
	}

	public long getLength() {
		if (segment != null) {
			return segment.getLength();
		}
		return 0;
	}

	/**
	 * @return the status
	 */
	public MixBlockStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(MixBlockStatus status) {
		this.status = status;
	}
	
	
}
