package com.mks.utilizer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple class to handle all sleeps </br>
 * <p>
 * <b>Note:</b> I am aware of org.openqa.selenium.support.ui.Sleeper</br>
 * This wraps the InterruptExeception in a RuntimeException so we don't have to
 * deal with a try catch block every time.
 * </p>
 * 
 * @author Manjunath KS
 *
 */
public class SoftSleeper {

	public enum Unit {
		MILLISECONDS(1), SECONDS(1000), MINUTES(60000);

		private int factor;

		private Unit(int factor) {
			this.factor = factor;
		}
	}

	private static final Logger logger = LogManager.getLogger(SoftSleeper.class);

	public static void milliseconds(long milliseconds) {
		try {
			logger.trace(String.format("Sleeping %d milliseconds", milliseconds));
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Do nothing for a fixed amount of time <br/>
	 * <br/>
	 * <b>Note:</b> Do not use to wait for guiObjects
	 * 
	 * @param milliseconds
	 *            - duration in milliseconds
	 * @param reason
	 *            - reason why you are sleeping i.e. "polling interval"
	 */
	public static void milliseconds(long milliseconds, String reason) {
		sleep((int) milliseconds, Unit.MILLISECONDS, reason, Level.TRACE);
	}

	public static void seconds(int seconds) {
		logger.trace(String.format("Sleeping %d seconds", seconds));
		sleep(seconds, Unit.SECONDS, String.format(" %s seconds", seconds), Level.TRACE);
	}

	/**
	 * Do nothing for a fixed amount of time <br/>
	 * <br/>
	 * <b>Note:</b> Do not use to wait for guiObjects
	 * 
	 * @param seconds
	 *            - duration in seconds
	 * @param reason
	 *            - reason why you are sleeping i.e. "polling interval"
	 */
	public static void seconds(int seconds, String reason) {
		sleep(seconds, Unit.SECONDS, reason, Level.TRACE);
	}

	/**
	 * Do nothing for a fixed amount of time <br/>
	 * <br/>
	 * <b>Note:</b> Do not use to wait for guiObjects
	 * 
	 * @param minutes
	 *            - duration in minutes
	 * @param reason
	 *            - reason why you are sleeping i.e. "polling interval"
	 */
	public static void minutes(int minutes, String reason, Level logLevel) {
		try {
			logger.log(logLevel, String.format("Sleeping %d minutes for %s", minutes, reason));
			int countdown = minutes;
			for (int i = countdown; i > 0; i--) {
				logger.log(logLevel, String.format("%d minutes...", i));
				Thread.sleep(Unit.MINUTES.factor);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Do nothing for a fixed amount of time <br/>
	 * <br/>
	 * <b>Note:</b> Do not use to wait for guiObjects
	 * 
	 * @param increments
	 *            - duration in number of units
	 * @param units
	 *            - milliseconds, seconds, minutes
	 * @param reason
	 *            - reason why you are sleeping i.e. "polling interval"
	 * @param logLevel
	 *            - logging level for wait statements
	 */
	public static void sleep(int increments, Unit units, String reason, Level logLevel) {
		try {
			logger.log(logLevel,
					String.format("Sleeping %d %s for %s", increments, units.name().toLowerCase(), reason));
			Thread.sleep(increments * units.factor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
