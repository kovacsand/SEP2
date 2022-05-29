package shared.utils;

import java.beans.PropertyChangeListener;

/**
 * Interface used for adding and removing listeners in Observer Pattern
 * @author S2G2
 * @version 1.0
 */
public interface Subject
{
  /**
   * Adding a PropertyChangeListener property to the listener list
   * @param propertyName  name of the property
   * @param listener listener that's added to the list
   */
  void addListener(String propertyName, PropertyChangeListener listener);

  /**
   * Removing a PropertyChangeListener property from the listener list
   * @param propertyName name of the property
   * @param listener listener that's removed from the list
   */
  void removeListener(String propertyName, PropertyChangeListener listener);
}