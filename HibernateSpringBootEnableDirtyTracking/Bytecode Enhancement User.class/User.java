package com.jpa;

import javax.persistence.Transient;
import org.hibernate.bytecode.enhance.internal.tracker.DirtyTracker;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.ManagedEntity;
import org.hibernate.internal.util.compare.EqualsHelper;


@javax.persistence.Entity
public class User
  implements java.io.Serializable, ManagedEntity, org.hibernate.engine.spi.SelfDirtinessTracker
{
  private static final long serialVersionUID = 1L;
  @javax.persistence.Id
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String city;
  
  public User() {}
  
  public Long getId() { return $$_hibernate_read_id(); }
  
  private int age;
  
  public void setId(Long id) { $$_hibernate_write_id(id); }
  


  public String getName() { return $$_hibernate_read_name(); }
  
  @Transient
  private transient EntityEntry $$_hibernate_entityEntryHolder;
  public void setName(String name) { $$_hibernate_write_name(name); }
  
  @Transient
  private transient ManagedEntity $$_hibernate_previousManagedEntity;
  public String getCity() { return $$_hibernate_read_city(); }
  
  public void setCity(String city)
  {
    $$_hibernate_write_city(city);
  }
  
  public int getAge() {
    return $$_hibernate_read_age();
  }
  

  public void setAge(int age) { $$_hibernate_write_age(age); }
  
  @Transient
  private transient ManagedEntity $$_hibernate_nextManagedEntity;
  
  public String toString() { return "User{id=" + $$_hibernate_read_id() + ", name=" + $$_hibernate_read_name() + ", city=" + $$_hibernate_read_city() + ", age=" + $$_hibernate_read_age() + '}'; }
  
  @Transient
  private transient DirtyTracker $$_hibernate_tracker;
  public Object $$_hibernate_getEntityInstance()
  {
    return this;
  }
  
  public EntityEntry $$_hibernate_getEntityEntry()
  {
    return $$_hibernate_entityEntryHolder;
  }
  
  public void $$_hibernate_setEntityEntry(EntityEntry paramEntityEntry)
  {
    $$_hibernate_entityEntryHolder = paramEntityEntry;
  }
  
  public ManagedEntity $$_hibernate_getPreviousManagedEntity()
  {
    return $$_hibernate_previousManagedEntity;
  }
  
  public void $$_hibernate_setPreviousManagedEntity(ManagedEntity paramManagedEntity)
  {
    $$_hibernate_previousManagedEntity = paramManagedEntity;
  }
  
  public ManagedEntity $$_hibernate_getNextManagedEntity()
  {
    return $$_hibernate_nextManagedEntity;
  }
  
  public void $$_hibernate_setNextManagedEntity(ManagedEntity paramManagedEntity)
  {
    $$_hibernate_nextManagedEntity = paramManagedEntity;
  }
  
  public void $$_hibernate_trackChange(String paramString)
  {
    if ($$_hibernate_tracker == null) {
      $$_hibernate_tracker = new org.hibernate.bytecode.enhance.internal.tracker.SimpleFieldTracker();
    }
    $$_hibernate_tracker.add(paramString);
  }
  
  public String[] $$_hibernate_getDirtyAttributes()
  {
    return $$_hibernate_tracker == null ? new String[0] : $$_hibernate_tracker.get();
  }
  
  public boolean $$_hibernate_hasDirtyAttributes()
  {
    return ($$_hibernate_tracker != null) && (!$$_hibernate_tracker.isEmpty());
  }
  
  public void $$_hibernate_clearDirtyAttributes()
  {
    if ($$_hibernate_tracker != null) {
      $$_hibernate_tracker.clear();
    }
  }
  
  public void $$_hibernate_suspendDirtyTracking(boolean paramBoolean)
  {
    if ($$_hibernate_tracker == null) {
      $$_hibernate_tracker = new org.hibernate.bytecode.enhance.internal.tracker.SimpleFieldTracker();
    }
    $$_hibernate_tracker.suspend(paramBoolean);
  }
  
  public org.hibernate.bytecode.enhance.spi.CollectionTracker $$_hibernate_getCollectionTracker()
  {
    return org.hibernate.bytecode.enhance.internal.tracker.NoopCollectionTracker.INSTANCE;
  }
  
  public Long $$_hibernate_read_id()
  {
    return id;
  }
  
  public void $$_hibernate_write_id(Long paramLong)
  {
    id = paramLong;
  }
  
  public String $$_hibernate_read_name()
  {
    return name;
  }
  
  public void $$_hibernate_write_name(String paramString)
  {
    if (!EqualsHelper.areEqual(name, paramString)) {
      $$_hibernate_trackChange("name");
    }
    name = paramString;
  }
  
  public String $$_hibernate_read_city()
  {
    return city;
  }
  
  public void $$_hibernate_write_city(String paramString)
  {
    if (!EqualsHelper.areEqual(city, paramString)) {
      $$_hibernate_trackChange("city");
    }
    city = paramString;
  }
  
  public int $$_hibernate_read_age()
  {
    return age;
  }
  
  public void $$_hibernate_write_age(int paramInt)
  {
    if (age != paramInt) {
      $$_hibernate_trackChange("age");
    }
    age = paramInt;
  }
}
