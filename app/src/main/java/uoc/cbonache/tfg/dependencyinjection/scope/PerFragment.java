package uoc.cbonache.tfg.dependencyinjection.scope;

/*
Created by Helm  25/1/17.
*/
import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {}

