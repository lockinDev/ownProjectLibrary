package models;

import java.util.*;
import dal.*;

// Adapter class to Publisher
public class Publishers 
{
  
  public static List<Publisher> getAll()
  {
    return PublisherData.getPublishers();
  }
  
  
  public static Publisher getPublisher(int id)
  {
    throw new UnsupportedOperationException();
  }
  
    
  public static boolean exists(String name)
  {
    return PublisherData.exists(name);
  }
  
  
  public static Publisher insert(String name)
  {
    name = Db.cut(name, Publisher.NAME);
    return PublisherData.insert(name);
  }
  
  
  public static boolean update(Publisher publisher)
  {
    publisher.setName(Db.cut(publisher.getName(), Publisher.NAME));
    return PublisherData.update(publisher);
  }

  
  public static boolean delete(int id)
  {
    return PublisherData.delete(id);
  }
}
