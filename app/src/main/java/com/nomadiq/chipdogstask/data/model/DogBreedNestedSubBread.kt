package com.nomadiq.chipdogstask.data.model

/**
 * @author Michael Akakpo
 *
 * This is the optional helper data class which is used to pass data about [DogSubBreed]
 */
// TODO - Not part of the spec, but this could be used to determine the number of SubBreeds belonging to a particular parent / 'master breed'
data class DogBreedNestedSubBread(
    val name: String,
    val subBreeds: String
)