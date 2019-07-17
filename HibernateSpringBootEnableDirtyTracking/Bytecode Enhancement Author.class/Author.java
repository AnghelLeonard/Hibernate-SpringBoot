package com.bookstore.entity;

import java.util.Objects;
import javax.persistence.Transient;
import org.hibernate.bytecode.enhance.internal.tracker.DirtyTracker;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.ManagedEntity;


@javax.persistence.Entity
public class Author
  implements java.io.Serializable, ManagedEntity, org.hibernate.engine.spi.SelfDirtinessTracker
{
  private static final long serialVersionUID = 1L;
  @javax.persistence.Id
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String genre;
  
  public Author() {}
  
  public Long getId() { return $$_hibernate_read_id(); }
  
  private int age;
  
  public void setId(Long id) { $$_hibernate_write_id(id); }
  


  public String getName() { return $$_hibernate_read_name(); }
  
  @Transient
  private transient EntityEntry $$_hibernate_entityEntryHolder;
  public void setName(String name) { $$_hibernate_write_name(name); }
  
  @Transient
  private transient ManagedEntity $$_hibernate_previousManagedEntity;
  public String getGenre() { return $$_hibernate_read_genre(); }
  
  public void setGenre(String genre)
  {
    $$_hibernate_write_genre(genre);
  }
  
  public int getAge() {
    return $$_hibernate_read_age();
  }
  
  public void setAge(int age) {
    $$_hibernate_write_age(age);
  }
  
  @Transient
  private transient ManagedEntity $$_hibernate_nextManagedEntity;
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
    if ($$_hibernate_tracker == null)
    {
      this;
      $$_hibernate_tracker = new org.hibernate.bytecode.enhance.internal.tracker.SimpleFieldTracker();
    }
    $$_hibernate_tracker.add(paramString);
  }
  
  public String[] $$_hibernate_getDirtyAttributes()
  {
    Object localObject = this;
    localObject = null;
    localObject = $$_hibernate_tracker == null ? new String[0] : $$_hibernate_tracker.get();
    return localObject;
  }
  
  public boolean $$_hibernate_hasDirtyAttributes()
  {
    Author localAuthor = this;
    boolean bool = false;
    bool = ($$_hibernate_tracker != null) && (!$$_hibernate_tracker.isEmpty());
    return bool;
  }
  
  public void $$_hibernate_clearDirtyAttributes()
  {
    if ($$_hibernate_tracker != null) {
      $$_hibernate_tracker.clear();
    }
  }
  
  public void $$_hibernate_suspendDirtyTracking(boolean paramBoolean)
  {
    if ($$_hibernate_tracker == null)
    {
      this;
      $$_hibernate_tracker = new org.hibernate.bytecode.enhance.internal.tracker.SimpleFieldTracker();
    }
    $$_hibernate_tracker.suspend(paramBoolean);
  }
  
  public org.hibernate.bytecode.enhance.spi.CollectionTracker $$_hibernate_getCollectionTracker()
  {
    Object localObject = this;
    localObject = null;
    localObject = org.hibernate.bytecode.enhance.internal.tracker.NoopCollectionTracker.INSTANCE;
    return localObject;
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
    if (!Objects.deepEquals(paramString, name)) {
      $$_hibernate_trackChange("name");
    }
    name = paramString;
  }
  
  public String $$_hibernate_read_genre()
  {
    return genre;
  }
  
  public void $$_hibernate_write_genre(String paramString)
  {
    if (!Objects.deepEquals(paramString, genre)) {
      $$_hibernate_trackChange("genre");
    }
    genre = paramString;
  }
  
  public int $$_hibernate_read_age()
  {
    return age;
  }
  
  public void $$_hibernate_write_age(int paramInt)
  {
    if (paramInt - age != 0) {
      $$_hibernate_trackChange("age");
    }
    age = paramInt;
  }
}