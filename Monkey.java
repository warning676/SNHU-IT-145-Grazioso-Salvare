
public class Monkey extends RescueAnimal {
	
	private double tailLength;
	private double height;
	private double bodyLength;
	private String species;
	
	public Monkey(String name, String gender, int age,
		    double weight, String acquisitionDate, String acquisitionCountry,
			String trainingStatus, boolean reserved, String inServiceCountry, 
			double tailLength, double height, double bodyLength, String species) {
		
		setName(name);
		setGender(gender);
		setAge(age);
		setWeight(weight);
		setAcquisitionDate(acquisitionDate);
		setAcquisitionLocation(acquisitionCountry);
		setTrainingStatus(trainingStatus);
		setReserved(reserved);
		setInServiceCountry(inServiceCountry);
		
		// Initialize monkey-specific attributes
		setTailLength(tailLength); // Species of monkey
		setHeight(height);         // Tail length in centimeters
		setBodyLength(bodyLength); // Height in centimeters
		setSpecies(species);       // Body length in centimeters
		setAnimalType("monkey"); // This line ensures animalType is set.
	}
	
	
	// Accessors and mutators
	public void setTailLength(double newTailLength) {
		tailLength = newTailLength;
	}
	
	public double getTailLength() {
		return tailLength;
	}
	
	public void setHeight(double newHeight) {
		height = newHeight;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setBodyLength(double newBodyLength) {
		bodyLength = newBodyLength;
	}
	
	public double getBodyLength() {
		return bodyLength;
	}
	
	public void setSpecies(String newSpecies) {
		species = newSpecies;
	}
	
	public String getSpecies() {
		return species;
	}
}
