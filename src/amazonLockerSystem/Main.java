package amazonLockerSystem;
/*
Questions
 1. What are the sizes of packages and locker?
 2. Package goes into a Locker and can a locker hold more than one package?
 3. What happens if I cannot find the appropriate locker size?
 4. How many lockers from size exist in pickup location does it change based on location?
 5. What if the package comes to a facility and I can't find any locker available to store it?
 6. What do you mean by finding an empty locker efficiently?

Functional Requirements
 Locker and Package Sizes - S, M, L (in future more sizes can be added)
 Package goes into a Locker (Only a single package)
 Find the locker which is the closest match to the package size, if not return False
 Need to have multiple pickup locations and their capacity will vary

----- core entities -------
AmazonHUB
  attributes: Map<String, PickupLocation> spots, Queue<Package> pendingProds,
  Queue<Package> rejectedProds
  apis:
    void addPickupSpot(PickupLocation pl)
    void addNewPackage(Package p)
    void sendPackages()
 - PickupLocation
    attributes: String loc, Map<String, Locker> lockers, Map<Size, Queue<Locker>> availableLockers
    apis:
      Locker store(Package p) - dont store a package in a small locker
      String getLoc()
      void addLocker(Locker l)
    - Lockers
      attributes: String locker_id, Size size, boolean occupied
      apis:
        void placePackage(Package p)
        Package pickupPackage()
        String getLockerId()
        - Packages
          attribute: String package_id, Size size
          apis:
            Size getSize()
            String getPackageId()


 */
public class Main {
  public static void main(String[] args) {

  }
}
