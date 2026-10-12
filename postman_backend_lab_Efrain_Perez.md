{
	"info": {
		"_postman_id": "a1b2c3d4-e5f6-7890-abcd-1234567890ab",
		"name": "API Spring Boot - Auth, Categorías y Productos",
		"description": "Colección de Postman con el orden correcto (Categorías antes de Productos) y con los objetos JSON estructurados para Producto y Categoría.",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"variable": [
		{
			"key": "baseUrl",
			"value": "http://localhost:8080",
			"type": "string"
		},
		{
			"key": "token",
			"value": "",
			"type": "string"
		}
	],
	"item": [
		{
			"name": "Auth",
			"item": [
				{
					"name": "Login - Admin (SUPER-ADMIN-ROLE)",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"if (pm.response.code === 200) {",
									"    var jsonData = pm.response.json();",
									"    var token = jsonData.token || jsonData.accessToken || jsonData.jwt || jsonData.jwtResponse;",
									"    if (token) {",
									"        pm.collectionVariables.set(\"token\", token);",
									"        console.log(\"Token guardado exitosamente:\", token);",
									"    }",
									"}"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"username\": \"admin\",\n  \"password\": \"admin123\"\n}"
						},
						"url": {
							"raw": "{{baseUrl}}/api/v1/auth/login",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"auth",
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "Login - Usuario (USER)",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"if (pm.response.code === 200) {",
									"    var jsonData = pm.response.json();",
									"    var token = jsonData.token || jsonData.accessToken || jsonData.jwt || jsonData.jwtResponse;",
									"    if (token) {",
									"        pm.collectionVariables.set(\"token\", token);",
									"        console.log(\"Token guardado exitosamente:\", token);",
									"    }",
									"}"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"username\": \"usuario\",\n  \"password\": \"user123\"\n}"
						},
						"url": {
							"raw": "{{baseUrl}}/api/v1/auth/login",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"auth",
								"login"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Categorías",
			"item": [
				{
					"name": "Listar Categorías (Público)",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{baseUrl}}/api/v1/categoria",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"categoria"
							]
						}
					},
					"response": []
				},
				{
					"name": "Obtener Categoría por ID (Público)",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{baseUrl}}/api/v1/categoria/1",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"categoria",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Registrar Categoría (SUPER-ADMIN-ROLE)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{token}}",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"nombre\": \"Tecnología\",\n  \"descripcion\": \"Equipos y dispositivos electrónicos\"\n}"
						},
						"url": {
							"raw": "{{baseUrl}}/api/v1/categoria",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"categoria"
							]
						}
					},
					"response": []
				},
				{
					"name": "Actualizar Categoría (SUPER-ADMIN-ROLE)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{token}}",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"nombre\": \"Tecnología y Gadgets\",\n  \"descripcion\": \"Categoría modificada con nuevos artículos\"\n}"
						},
						"url": {
							"raw": "{{baseUrl}}/api/v1/categoria/1",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"categoria",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Eliminar Categoría (SUPER-ADMIN-ROLE)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{token}}",
									"type": "string"
								}
							]
						},
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "{{baseUrl}}/api/v1/categoria/1",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"categoria",
								"1"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Productos",
			"item": [
				{
					"name": "Listar Productos (Público)",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{baseUrl}}/api/v1/producto",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"producto"
							]
						}
					},
					"response": []
				},
				{
					"name": "Registrar Producto (SUPER-ADMIN-ROLE)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{token}}",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"nombre\": \"Laptop Gamer\",\n  \"descripcion\": \"Laptop de alta gama para videojuegos\",\n  \"precio\": 1200.00,\n  \"cantidadEnStock\": 10,\n  \"categoria\": {\n    \"id\": 1,\n    \"nombre\": \"Tecnología\",\n    \"descripcion\": \"Equipos y dispositivos electrónicos\"\n  }\n}"
						},
						"url": {
							"raw": "{{baseUrl}}/api/v1/producto",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"producto"
							]
						}
					},
					"response": []
				},
				{
					"name": "Actualizar Producto (SUPER-ADMIN-ROLE)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{token}}",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"nombre\": \"Laptop Gamer Pro\",\n  \"descripcion\": \"Edición actualizada con mejor gráfica\",\n  \"precio\": 1350.00,\n  \"cantidadEnStock\": 8,\n  \"categoria\": {\n    \"id\": 1,\n    \"nombre\": \"Tecnología\",\n    \"descripcion\": \"Equipos y dispositivos electrónicos\"\n  }\n}"
						},
						"url": {
							"raw": "{{baseUrl}}/api/v1/producto/1",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"producto",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Eliminar Producto (SUPER-ADMIN-ROLE)",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "{{token}}",
									"type": "string"
								}
							]
						},
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "{{baseUrl}}/api/v1/producto/1",
							"host": [
								"{{baseUrl}}"
							],
							"path": [
								"api",
								"v1",
								"producto",
								"1"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}