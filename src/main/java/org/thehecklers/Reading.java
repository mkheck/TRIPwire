package org.thehecklers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Reading {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private double hum, temp;
	private long cpm;
	private int heading;

	protected Reading() {}
	
	public Reading(int id, double hum, double temp, long cpm, int heading) {
		this.id = id;
		this.hum = hum;
		this.temp = temp;
		this.cpm = cpm;
		this.heading = heading;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHum() {
		return hum;
	}

	public void setHum(double hum) {
		this.hum = hum;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getCpm() {
		return cpm;
	}

	public void setCpm(long cpm) {
		this.cpm = cpm;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	@Override
	public String toString() {
		return "Reading [id=" + id + ", hum=" + hum + ", temp=" + temp + ", radiation cpm=" + cpm +
				", heading=" + heading + "]";
	}
}
