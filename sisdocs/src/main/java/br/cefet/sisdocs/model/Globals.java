package br.cefet.sisdocs.model;

// Drive system globals
public class Globals {
	public static final int kb = 1024;
	public static final int mb = kb * 1024;
	public static final int gb = mb * 1024;
	public static final int tb = gb * 1024;
	
	
	public static String CalcSize(long filesize) {
		String size = "";
		if(filesize >= kb && filesize < mb) {
			filesize = filesize/kb;
			size = filesize+"kb";
		}
		else if(filesize >= mb && filesize < gb) {
			filesize = filesize/mb;
			size = filesize+"mb";
		}
		else if(filesize >= gb && filesize <= tb) {
			filesize = filesize/gb;
			size = filesize+"gb";
		}
		return size;
	}
}
