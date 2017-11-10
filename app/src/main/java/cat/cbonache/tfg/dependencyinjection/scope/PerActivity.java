/*
Created by Helm  25/1/17.
*/


package cat.cbonache.tfg.dependencyinjection.scope;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}

