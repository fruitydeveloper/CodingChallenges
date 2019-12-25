/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version);
      
      Minimize API calls by using Binary search
*/
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        
        while (l < r) {
            int version = l + (r - l) / 2;
            boolean isBad = isBadVersion(version);
            if(isBad) {
                r = version;
            } else {
                l = version + 1;
            }
        }
        
        return l;
    }
}