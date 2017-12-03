package uoc.cbonache.tfg.model.exceptions

/**
 * @author cbonache
 */
sealed class ShippingException : Exception() {

    class NoShippingException : ShippingException()

}