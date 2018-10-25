package com.jpa;

import com.blazebit.persistence.view.EntityView;

@EntityView(Car.class)
public interface CarView {

    public String getName();

    public String getColor();
}
