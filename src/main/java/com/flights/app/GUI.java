package com.flights.app;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class GUI extends Frame {

	public JSONArray array;

	public GUI(JSONArray array) {
		this.array = array;
		setWindowsInfo();
	}

	public void setWindowsInfo() {
		setVisible(true);
		setSize(1080, 720);
		setTitle("Covid19 in Italy");
		setLayout(new FlowLayout());
		setResizable(true);

		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	public void parseInfo() throws IOException {

		FileWriter f = new FileWriter("covid_info.txt");

		Label l = new Label("Covid Info in Italy");
		this.add(l);

		for (int i = 0; i < this.array.length(); i++) {
			JSONObject data = this.array.getJSONObject(i);
			JSONObject r = data.getJSONObject("region");
			String last_update = data.getString("last_update");
			String region = r.getString("province");
			int confirmed = data.getInt("confirmed");
			int deaths = data.getInt("deaths");
			float fatality_rate = data.getFloat("fatality_rate");

			String info = "Last update : " + last_update + "\n" + region + "\n\t confirmed: " + confirmed
					+ "\n\t deaths: " + deaths + "\n\t fatality_rate: " + fatality_rate;

			TextArea t = new TextArea(info);
			t.setEditable(false);
			t.setMaximumSize(new Dimension(200, 100));
			this.add(t);

			f.write(info + "\n\n");
		}

		f.close();
	}

}
