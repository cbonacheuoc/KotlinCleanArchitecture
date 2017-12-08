package uoc.cbonache.tfg.ui

/**
 * @author cbonache
 */
enum class ShippingStates(val state: Int) {

    AT_DELIVERY_OFFICE(1),
    IN_TRANSIT(2),
    DELIVERED(3),
    USER_NOT_FOUND(4),
    WRONG_ADDRESS(5)
}