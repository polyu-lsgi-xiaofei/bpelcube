package gr.uoa.di.s3lab.p2p;

import java.util.Date;

/**
 * Implements a very simple, console-based logging utility.
 * 
 * @author Michael Pantazoglou
 *
 */
public class Log {
	
	public static enum Level {
		OFF(-1), 
		INFO(0), 
		WARNING(1), 
		ERROR(2), 
		DEBUG(3);
		
		private final int numericValue;
		
		Level(int n) {
			numericValue = n;
		}
		
		public final int getNumericValue() {
			return numericValue;
		}
	}
	
	private String ownerClass;
	
	private Level level;
	
	public Log(Class<?> c, Level l) {
		ownerClass = c.getSimpleName();
		level = l;
	}
	
	public void log(Level l, String msg) {
		if (l.getNumericValue() <= level.getNumericValue()) {
			StringBuilder sb = new StringBuilder();
			sb.append(new Date(System.currentTimeMillis())).append(" ");
			sb.append("[").append(ownerClass).append("]");
			sb.append("[").append(level.toString()).append("] ");
			sb.append(msg);
			System.out.println(sb.toString());
		}
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void info(String msg) {
		log(Level.INFO, msg);
	}
	
	public void warning(String msg) {
		log(Level.WARNING, msg);
	}
	
	public void error(String msg) {
		log(Level.ERROR, msg);
	}
	
	public void debug(String msg) {
		log(Level.DEBUG, msg);
	}

}
