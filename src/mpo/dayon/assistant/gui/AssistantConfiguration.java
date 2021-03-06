package mpo.dayon.assistant.gui;

import mpo.dayon.common.configuration.Configuration;
import mpo.dayon.common.preference.Preferences;
import mpo.dayon.common.utils.SystemUtilities;

public class AssistantConfiguration extends Configuration {
	private static final String PREF_VERSION = "assistant.version";

	private static final String PREF_LOOK_AND_FEEL = "assistant.lookAndFeel";

	private final String lookAndFeelClassName;

	/**
	 * Default : takes its values from the current preferences.
	 *
	 * @see mpo.dayon.common.preference.Preferences
	 */
	public AssistantConfiguration() {
		final Preferences prefs = Preferences.getPreferences();

		// Note: did not exist in version = 0 => no migration is required.

		lookAndFeelClassName = prefs.getStringPreference(PREF_LOOK_AND_FEEL, SystemUtilities.getDefaultLookAndFeel());
	}

	public AssistantConfiguration(String lookAndFeelClassName) {
		this.lookAndFeelClassName = lookAndFeelClassName;
	}

	public String getLookAndFeelClassName() {
		return lookAndFeelClassName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final AssistantConfiguration that = (AssistantConfiguration) o;

		return lookAndFeelClassName.equals(that.lookAndFeelClassName);
	}

	@Override
	public int hashCode() {
		return lookAndFeelClassName.hashCode();
	}

	/**
	 * @param clear
	 *            allows for clearing properties from previous version
	 */
	protected void persist(boolean clear) {
		final Preferences.Props props = new Preferences.Props();
		{
			props.set(PREF_VERSION, String.valueOf(1));
			props.set(PREF_LOOK_AND_FEEL, lookAndFeelClassName);
		}

		Preferences.getPreferences().update(props); // atomic (!)
	}

}
