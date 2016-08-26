package tsp;

/**
 * Clase Clock
 * @author alu4644
 */

public class Clock {
	long inicioT;
	long finalT;
	
	public Clock () {
		inicioT = 0;
		finalT = 0;
	}
	
	public void start () {
		inicioT = System.nanoTime();
	}
	
	public void stop () {
		finalT = System.nanoTime();
	}
	
	public void reset () {
	  inicioT = 0;
	  finalT = 0;
	}
	
	public long elapsedTime () {
		return (getFinal() - getInicio());
	}
	
	public long getInicio () {
		return inicioT;
	}
	
	public long getFinal () {
		return finalT;
	}
	
	
}
