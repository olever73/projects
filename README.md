Homework project:
POST /api/buildings?apartmentsCount=5
201 - CREATED
409 - CONFLICT
GET /api/buildings?street=Abc
Returns all buildings on the street
Desired fields:
•	id
•	address
•	apartments count
GET /api/buildings/:id/apartments?hasOwners=true
Returns all apartments
hasOnwers is not required, but if ?hasOwners=true - return apartments with owners only
API should return apartments with the list of all owners Note: lists cannot be nullable, lists should be empty ( [ ] )
200 - OK
404 - NOT FOUND
PUT /api/buildings/:id/apartments/:id/owners/:id
Move in an owner to the apartment
200 - OK
404 - NOT FOUND - should have different messages for each 404
DELETE /api/buildings/:id/apartments/:id/owners/:id
Evict an owner from the apartment
GET /api/owners/:id
Return
•	owner
•	list of owner's apartments
200 - OK
404 - NOT FOUND
DELETE /api/buildings/:id/demolish
Destroy/Delete the building
Reply to chat:
•	Why it is not allowed to demolish the building?
•	Who blocks the deletion: JPA, Hibernate or Spring Boot? (or something else?)
Entities
Building
•	id: Long
•	street: String
•	house: String
Apartment
•	id: Long
•	apartmentNumber: Integer
•	hasBalcony: Boolean
•	building: Building
Owner
•	id: Long
•	name: String
•	apartment: Apartment
Buildings
Buildings should be unique by address and house number.
Should check, if such a building exists on the street and with such house number before creating one.
Building request has a number of apartments to create (?apartmentsCount=3)
Apartments should be created automatically on a building creation.
Reply to chat: what is the relationship between Building and Apartment & Apartment with Owner


