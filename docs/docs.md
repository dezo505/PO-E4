Link do githuba: https://github.com/dezo505/PO-E4


# Modele

## Category

Klasa reprezentująca kategorię produktu w sklepie internetowym.

### Atrybuty:

- `id` - Unikalny identyfikator kategorii
- `name` - Nazwa kategorii

### Metody:

#### `Category(String name)`

Konstruktor klasy Category przyjmujący nazwę kategorii.

**Parametry:**
- `name` - Nazwa kategorii

## Product

Klasa reprezentująca produkt w sklepie internetowym.

### Atrybuty:

- `id` - Unikalny identyfikator produktu
- `name` - Nazwa produktu
- `description` - Opis produktu
- `price` - Cena produktu
- `stockQuantity` - Ilość produktu dostępna na stanie
- `category` - kategoria do której należy produkt

### Metody:

#### `Product(String name, String description, double price, int stockQuantity, Category category)`

Konstruktor klasy Product.

**Parametry:**
- `name` - Nazwa produktu
- `description` - Opis produktu
- `price` - Cena produktu
- `stockQuantity` - Ilość produktu dostępna na stanie
- `category` - kategoria do której należy produkt

# Repozytoria

## Category Repository

Repozytorium do operacji na danych kategorii w bazie danych, wszystkie metody są generowane automatycznie przez springa.

## Product Repository

Repozytorium do operacji na danych produktów w bazie danych, wszystkie metody są generowane automatycznie przez springa.

# Serwisy

## Category Service

Serwis do obsługi operacji na danych kategorii.

### Metody:

`List<Category> getAllCategories()`

Pobiera wszystkie kategorie z repozytorium.

## Product Service

Serwis do obsługi operacji na danych produktów.

### Metody:

#### `Product getProductById(Long id)`

Pobiera produkt o określonym ID, jeżeli produkt o podanym id nie istnieje zwraca `null`

**Parametry:**

- `id` - id szukanego produktu



#### `List<Product> filterProducts(String search, BigDecimal minPrice, BigDecimal maxPrice, Long categoryId, String availability)`

Zwraca listę przefiltrowanych produktów na podstawie podanych kryteriów, jeżeli jakieś kryterium jest równe `null` nie jest brane pod uwagę przy filtrowaniu.

**Parametry:**
- `search` - Tekst wyszukiwany w nazwie produktu
- `minPrice` - Minimalna cena produktu
- `maxPrice` - Maksymalna cena produktu
- `categoryId` - ID kategorii produktu
- `availability` - Dostępność produktu (`"all"` lub `"available"`)

# Kontroler

## Product Controller

Kontroler obsługujący żądania związane z produktami.

### Endpointy:

#### `/product/details/{id}`

Wyświetla szczegóły produktu o określonym ID.

parametry w ścieżce:
- `id` - id produktu, któego sczegóły są wyświetlane


#### `/product/list`

Wyświetla przefiltrowaną listę produktów.

parametry:

- `search` - Tekst wyszukiwany w nazwie produktu
- `minPrice` - Minimalna cena produktu
- `maxPrice` - Maksymalna cena produktu
- `categoryId` - ID kategorii produktu
- `availability` - Dostępność produktu (`"all"` lub `"available"`)
