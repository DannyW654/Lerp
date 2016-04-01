/**
 * A container to hold on to three values, each possibly a distinct
 * type.  Useful for returning multiple values.
 * 
 * @author Arthur Nunes-Harwitt
 */

public class Triple <X, Y, Z> {
 
  private X x;
  private Y y;
  private Z z;
  
  /**
   * Construct a Triple.
   *
   * @param x the first component of the triple
   * @param y the second component of the triple
   * @param z the third component of the triple
   */
  public Triple(X x, Y y, Z z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * A selector for extracting components of the triple.
   * 
   * @return the first component of the triple
   */
  public X first(){
      return this.x;
  }

  /**
   * A selector for extracting components of the triple.
   * 
   * @return the second component of the triple
   */
  public Y second(){
      return this.y;
  }

  /**
   * A selector for extracting components of the triple.
   * 
   * @return the third component of the triple
   */
  public Z third(){
      return this.z;
  }
  
}