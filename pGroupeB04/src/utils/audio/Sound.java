package utils.audio;

import enumeration.Path;
import serialisation.Serialization;

public class Sound {
	private double volume;
	
	public Sound() {
		
	}
	public Sound(double volume) {
		this.volume=volume;
	}
	
	public void setSound(Sound sound) {
		volume=sound.getVolume();
	}
	
	public double getVolume() {
		return volume;
	}
	
	/**
     * transforms a Sound object into json format
     * @param volume
     */
    public void toJson(double volume) {
        setSound(new Sound(volume));
        Serialization.writeJson(Path.VOLUME_SOUND.getPath(),this);
    }

    /**
     * transforms a json into a Sound object
     */
    public void fromJson() {
        this.setSound(Serialization.readJson(Path.VOLUME_SOUND.getPath(), this.getClass()));
    }

	
}
