package uoc.cbonache.tfg.async


interface PostExecutionThread {

    fun <T> submit(function: () -> T?)
}