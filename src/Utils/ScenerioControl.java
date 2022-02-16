package Utils;

public class ScenerioControl {

	private static final String SCENERIO = "develop";

	public static String getScenerio() {
		return SCENERIO;
	}

	public static boolean isInDevelop() {
		return "develop".equals(getScenerio());
	}
}
