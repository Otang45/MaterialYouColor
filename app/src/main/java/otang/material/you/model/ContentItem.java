package otang.material.you.model;

public class ContentItem {

	private String app;
	private String system;
	private int color;

	public ContentItem(String app, String system, int color) {
		this.app = app;
		this.system = system;
		this.color = color;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}