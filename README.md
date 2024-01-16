# JavaWebProject

## Food Delivery Application

My project will consist of a food delivery application. This application will allow users to order food from different restaurant/companies.
The user will be able to fill up his shopping cart with items from a certain restaurant and then order the food. The food will
be delivered by a delivery driver who is part of our application's system. 
Each restaurant can add menus .
Each restaurant can have multiple menus based on different themes they want to use 
(eg. Fitness Menu, Heavy Menu, Vegan Menu, All In One Menu). 
Each restaurant can have only one address.
Each menu will have multiple items on it. 
A user can choose a restaurant, then he can choose one of the available menus and then choose items and create an Order.  
Each user can have multiple addresses where he may want its food to be delivered. 
Each User/Restaurant/Driver will have an Order History.
Each driver can work for one or multiple restaurants. When a driver is hired, by default,
he can deliver from any restaurant, if it so happens that one restaurant and one driver to not get along well, then that restaurant
will be subtracted from the list of restaurants that driver can deliver from. 

Tables: 
- User
- Restaurant
- Driver
- Item
- Menu
- Order
- Address
- RestaurantDriverAssociation
- OrderItemAssociation

## API Endpoints

1. **Register User** 

- Path: `/api/users/register`
- Type: `POST`
- Body: `{name: String, email: String, password:String, phoneNumber:String}`
- Response: `{ userId: UUID, name: String, email: String, phoneNumber:String }`

2. **Login User**

- Path: `/api/users/login`
- Type: `POST`
- Body: `{ email: String, password:String}`
- Response: `{ token: String, userId: UUID, name: String, email: String}`

3. **Register Driver**

- Path: `/api/drivers/register`
- Type: `POST`
- Body: `{name: String, email: String, password:String, phoneNumber:String, vehicle: String, age: Int, deliveryExperience: String }`
- Response: `{ driverId: UUID, name: String, email: String, phoneNumber:String, vehicle: String, age: Int, deliveryExperience: String }`

4. **Login Driver**

- Path: `/api/drivers/login`
- Type: `POST`
- Body: `{ email: String, password:String}`
- Response: `{ token: String, userId: UUID, name: String, email: String}`

5. **Register Restaurant**

- Path: `/api/restaurants/register`
- Type: `POST`
- Body: `{name: String, email: String, password:String, priceRating: Int, Rating: Int, FoodType:List<String>, supportPhoneNumbers: List<String>}`
- Response: `{ restaurantId: UUID, name: String, email: String }`

6. **Login Restaurant**

- Path: `/api/restaurants/login`
- Type: `POST`
- Body: `{ email: String, password: String}`
- Response: `{ token: String, restaurantId: UUID,  name: String, email: String }`

7. **Get Restaurants**

- Path: `/api/restaurants`
- Type: `GET`
- Response: `[{ restaurantId: UUID,  name: String, priceRating: Int, Rating: Int, FoodType:List<String>, supportPhoneNumbers: List<String> },...]` 
    
8. **Create Menu**

- Path: `/api/menus/`
- Type: `POST`
- Body: `{ restaurantId: UUID, name: String, availability: Boolean}`
- Response: `{ menuId: UUID, restaurantId: UUID,  name: String,  availability: Boolean}`

9. **Get Menus By restaurantId**

- Path: `/api/menus/{restaurantId}/menus`
- Type: `GET`
- Params: `restaurantId: UUID`
- Response: `[{ menuId: UUID, restaurantId: UUID,  name: String,  availability: Boolean },...]` 

10. **Update Menu Availability**

- Path: `/api/menus/{menuId}/availability`
- Type: `PUT`
- Params: `menuId (UUID)`
- Body: `{ availability: Boolean}`
- Response: `{ menuId: UUID, restaurantId: UUID,  name: String,  availability: Boolean}`

11. **Delete Menu**

- Path: `/api/menus/{menuId}`
- Type: `DELETE`
- Params: `menuId (UUID)`
- Response: `{ message: String }`

12. **Create Item**

- Path: `/api/item/`
- Type: `POST`
- Body: `{ menuId: String, name: String, price: Float}`
- Response: `{ itemId, menuId: String, name: String, price: Float}`

13. **Get Items By menuId**

- Path: `/api/item/{menuId}`
- Type: `GET`
- Params: `menuId: UUID`
- Response: `[{ itemId, menuId: String, name: String, price: Float},...]` 

14. **Delete Item**

- Path: `/api/item/{itemId}`
- Type: `DELETE`
- Params: `itemId (UUID)`
- Response: `{ message: String }`

15. **Create Order**

- Path: `/api/order/`
- Type: `POST`
- Body: `{ userId: UUID, restaurantId:UUID, driverId:UUID, addressId: UUID, status: string}`
- Response: `{ orderId: UUID, userId: UUID, restaurantId:UUID, driverId:UUID, addressId: UUID, status: string}`

16. **Get Orders By userId**

- Path: `/api/order/user/{userId}`
- Type: `GET`
- Params: `userId: UUID`
- Response: `[{ orderId: UUID, userId: UUID, restaurantId:UUID, driverId:UUID, addressId: UUID, status: string},...]` 

17. **Get Orders By restaurantId**

- Path: `/api/order/restaurant/{restaurantId}`
- Type: `GET`
- Params: `restaurantId: UUID`
- Response: `[{ orderId: UUID, userId: UUID, restaurantId:UUID, driverId:UUID, addressId: UUID, status: string},...]` 

18. **Get Orders By driverId**

- Path: `/api/order/driver/{driverId}`
- Type: `GET`
- Params: `driverId: UUID`
- Response: `[{ orderId: UUID, userId: UUID, restaurantId:UUID, driverId:UUID, addressId: UUID, status: string},...]` 

19. **Update Order status**

- Path: `/api/order/{orderId}/status`
- Type: `PUT`
- Params: `orderId (UUID)`
- Body: `{ status: String}`
- Response: `{ orderId: UUID, userId: UUID, restaurantId:UUID, driverId:UUID, addressId: UUID, status: string}`

20. **Delete Order**

- Path: `/api/order/{orderId}`
- Type: `DELETE`
- Params: `orderId (UUID)`
- Response: `{ message: String }`

21. **Add item to Order**

- Path: `api/order/item/association`
- Type: `POST`
- Params: `orderId (UUID)`
- Body: `{ orderId: UUID, itemId: UUID}`
- Response: `{ orderItemAssociationId: UUID,orderId: UUID, itemId: UUID}`

22. **Remove item from Order**

- Path: `api/order/item/association`
- Type: `DELETE`
- Body: `{ orderId: UUID, itemId: UUID}`
- Response: `{ message: String }`

23. **Add driver to restaurant**

- Path: `api/restaurant/driver/association`
- Type: `POST`
- Body: `{ restaurantId: UUID, driverId: UUID}`
- Response: `{ restaurantDriverAssociationId: UUID, restaurantId: UUID, driverId: UUID}`

24. **Remove driver from restaurant**

- Path: `api/restaurant/driver/association`
- Type: `DELETE`
- Body: `{ restaurantId: UUID, driverId: UUID}`
- Response: `{ message: String }`

25. **Add address for user**

- Path: `api/address/`
- Type: `POST`
- Body: `{ userId: UUID, city: String,street: String, house: String, details: String}`
- Response: `{ addressId: UUID, userId: UUID, city: String,street: String, house: String, details: String}`

26. **Get all addresses by userId**

- Path: `/api/address/all/{userId}`
- Type: `GET`
- Params: `userId: UUID`
- Response: `[{ addressId: UUID, userId: UUID, city: String,street: String, house: String, details: String},...]` 

27. **Get single address**

- Path: `/api/address/single/{addressId}`
- Type: `GET`
- Params: `addressId: UUID`
- Response: `{ addressId: UUID, userId: UUID, city: String,street: String, house: String, details: String}` 

28. **Delete address**

- Path: `api/address/{addressId}`
- Type: `DELETE`
- Params: `addressId: UUID`
- Response: `{ message: String }`





