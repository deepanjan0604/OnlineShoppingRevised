var app = angular.module('app', ['ngRoute']);

app.config([
		'$httpProvider',
		function($httpProvider) {
			$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		} ]);



app.config(['$routeProvider',
         function($routeProvider) {
           $routeProvider
           .when('/login', {
               templateUrl: 'login.html',
               controller: 'loginctrl'
       
      })
       .when('/home', {
         templateUrl: 'home.html',
         controller: 'homectrl'
       })

           .when('/gridview', {
         templateUrl: 'gridview.html',
         controller: 'gridctrl'
       })
      .when('/cart', {
        templateUrl: 'cart2.html',
         controller: 'cartctrl'
       })
  
      .when('/listview', {
        templateUrl: 'listview.html',
         controller: 'listctrl'
      })
   
               .when('/newcustomer', {
                 templateUrl: 'newcustomer.html',
                 controller: 'newcustomerctrl'
               })
                         .when('/editcustomer', {
         templateUrl: 'editcustomer.html',
         controller: 'editcustomerctrl'
       })     
               
                .when('/editshipping/:id', {
                 templateUrl: 'editshipping.html',
                 controller: 'editshippingctrl'
               })
			    
             .when('/editproduct/:id', {
                 templateUrl: 'editproduct.html',
                 controller: 'editproductctrl'
               })
    
              .when('/viewcustomer', {
               templateUrl: 'viewcustomer.html',
               controller: 'viewcustomerctrl'
             })
             .when('/viewproduct', {
               templateUrl: 'viewproducts2.html',
               controller: 'productctrl'
             })
             .when('/addproducts', {
                   templateUrl: 'addproduct.html',
                   controller: 'addproductctrl'
                 }) /*.when('/',{
                	 templateUrl:'main.html',
                	 controller:'mainctrl'
                 })*/
                 
                 
                 .otherwise({
                     redirectTo: '/home'
                    	 
                   })
                   
         }]);

app.run(function($rootScope, $http, $location){
	
	$http({
		method : 'GET',
		url : '/categories',     
	
	}).then(function(response) {
		$rootScope.categories = response.data;
		
	});


$http({
				method : 'GET',
				url : '/log',     
				
			}).then(function(response) {
				
				$rootScope.response = response.data;
				debugger;
				if($rootScope.response.status=='true')
					{
/*					$rootScope.name=$rootScope.response.name;
*/				//alert('authentication Successfull');
$rootScope.authenticated = true;
if($rootScope.response.role=='user')
	{
	$rootScope.user=true;
	$rootScope.admin=false;

//alert($rootScope.user);
	}
else
	{
	$rootScope.admin=true;
	$rootScope.user=false;

	//alert($rootScope.admin);
	}
			
				$location.path('/');
				
					}		
		
	
		else{
			$rootScope.authenticated = false;
			/*alert('authentication failed');
			alert($rootScope.authenticated);*/
		}
	
})

$rootScope.logOut= function(){
	//alert('Logout Successfully')
	$http({
		method : 'POST',
		url : 'logout',     
	
	}).then(function(response) {
		
		
		
	});
	$location.path('/login');
	$rootScope.authenticated = false;
	$rootScope.admin=false;
	$rootScope.user=false;
}
})

	


app.controller('loginctrl',[ '$scope', '$rootScope','$http', '$location', function($scope,$rootScope, $http, $location)
{

//	$scope.auth = {};
//	$scope.login = function(){
//		$rootScope.loadCustomers($scope.auth);
//	}
	
	
		/*if(auth)
			{*/
			
		/*var headers = {
				'Authorization' : 'Basic ' + encodedAuthData
				}
			} else {
				headers : {};
			}*/
	$scope.login = function() {
		var authData = $scope.auth.username + ':' + $scope.auth.password;
		var encodedAuthData = btoa(authData);
			$http({
				method : 'GET',
				url : '/log',     
				headers : {'Authorization' : 'Basic ' + encodedAuthData}
			}).then(function(response) {
				
				$rootScope.response = response.data;
			//	alert('authentication Successfull');
$rootScope.authenticated = true;
$rootScope.authenticatedadd=true;
if($rootScope.response.role=='user')
	{
	$rootScope.user=true;
	$rootScope.admin=false;
	//$rootScope.authenticatedadd=false;
//alert($rootScope.user);
	}
else
	{
	$rootScope.admin=true;
	$rootScope.user=false;
	//$rootScope.authenticatedadd=false;
	//alert($rootScope.admin);
	}
			
				$location.path('/');
				
				
		}, function(response){
			$rootScope.authenticated = false;
			alert('authentication failed');
			alert($rootScope.authenticated);
		});
	}
}]);	




/*app.run(function($rootScope, $http){
	$rootScope.loadUsers = function(auth) {
		if(auth)
			{
			var authData = auth.username + ':' + auth.password;
		var encodedAuthData = btoa(authData);
		headers = {
				'Authorization' : 'Basic ' + encodedAuthData
				}
			} else {
				headers : {};
			}
			$http({
				method : 'GET',
				url : '/users',     
				headers : headers
			}).then(function(response) {
				$rootScope.users = response.data;
				$rootScope.authenticated = true;
		});
		};
	});*/



app.controller('productctrl',[ '$scope', '$rootScope','$http',function($scope,$rootScope, $http)
                          	     {
	$scope.title="List Of Products";
	//$scope.products={};
	$http({
		method : 'GET',
		url : '/viewproducts',	
	}).then(function(response) {
	
		$rootScope.products = angular.copy(response.data);		 
	});
                          			  }]);




app.controller('homectrl', ['$scope', function($scope) { 
	   
	   
}])


app.controller('viewcustomerctrl', [ '$scope','$route','$routeParams', '$rootScope','$http',
                               	     function($scope,$route,$routeParams,$rootScope, $http){
    
	$http({
			
		   method : 'GET',
	                           			
	                           			
	       url : '/customers/one',
	                           			
	        }).then(function(response) {
	                           			$scope.customer = angular.copy(response.data);
	                           			 
	                           		});
	
	
	 $http({
			method : 'GET',
			url : '/shippingaddresses/one',
			
		}).then(function(response) {
			$rootScope.shippingaddresses = angular.copy(response.data);
			 
		});
}])


			 
app.controller('editcustomerctrl',[ '$scope','$route','$routeParams', '$rootScope','$http',
                           	     function($scope,$route,$routeParams,$rootScope, $http)
                           	     {
                           			 
    $scope.title=' Edit Customer!!!';
                           	    
                  	    
    $http({
		method : 'GET',
		url : '/displaystate',     
	
	}).then(function(response) {
		$rootScope.statevat = response.data;
		
});
    
   $http({
                           			
	   method : 'GET',
                           			
                           			
       url : '/customers/one',
                           			
        }).then(function(response) {
                           			$scope.edit = angular.copy(response.data);
                           			 
                           		});
                           	    
                           	    
 /*$scope.edit={
		 customers:{
			 
			 }                      				
 }
  */                         		
    $scope.savedetails = function(){	
			
  		 $http({
  				method: 'POST',
  				url : '/savedetails',
  				data : $scope.edit,
  			
  			}).then(function(response){
  				if(response.data.status){
  					alert('customer edit Successfully!');
  					$scope.edit= {};
  					
  				} else {
  					alert('customer edit Failed!');
  				}
  			});
  			};     
// $scope.addEntry=function(){
//            $scope.shippingAddresses.push($scope.shippingAddress);
//             $scope.shippingAddress='';
//    }
	
	/*$scope.shippingAddresses=[];
	$scope.shippingAddress={};
	
	 $http({
			method : 'GET',
			url : '/addshippingaddress',
			
		}).then(function(response) {
			$scope.shippingAddress = angular.copy(response.data);
			 
		});
     */
  			//$rootScope.shippingAddress={};
  			
  			 $http({
  				method : 'GET',
  				url : '/shippingaddresses/one',
  				
  			}).then(function(response) {
  				$rootScope.shippingaddresses = angular.copy(response.data);
  				 
  			});
  			 
  
	 $scope.addShipping = function(){
		
			$http({
				method: 'POST',
				url : '/addshippingaddress',
				data : $scope.shippingAddress
			}).then(function(response){
				if(response.data.status){
					alert('shippingaddress Added Successfully!');
					
					
				
				/*	$rootScope.shippingaddresses.push($rootScope.shippingAddress);	
					
				     $scope.shippingAddress='';*/ 	
												} 
				
				else {
					alert('shippingaddress Addition Failed!');
				       }
			});
		};
		
	   			
                           	     }]);

			  



			                            			  	  

 app.controller('newcustomerctrl', [ '$scope', '$route','$routeParams','$rootScope','$http',
			                    			 function($scope,$route,$routeParams,$rootScope, $http) {
	$scope.title="New Customer";
	$scope.customers={};
	
	 $scope.savecustomer = function(){
		 $scope.customers.role="USER";	
			
			$http({
				method: 'POST',
				url : '/savecustomer',
				data : $scope.customers
			}).then(function(response){
				if(response.data.status){
					alert('Customer Added Successfully!');
					
					
												} 
				
				else {
					alert('Customer Addition Failed!');
				       }
			});
		};
		
		
		
	
	} ]);
 
 
 

 
 

 
 
 
  app.controller('editshippingctrl',[ '$scope','$route','$routeParams', '$rootScope','$http',
	     function($scope,$route,$routeParams,$rootScope, $http)
	     {
	  
	  $http({
			method : 'GET',
			url : '/displaystate',     
		
		}).then(function(response) {
			$rootScope.statevat = response.data;
			
	});
	  
	  
	  
			 
	  $http({
 			
		   method : 'GET',
	                           			
	                           			
	       url : '/shippingaddress/'+$routeParams.id,
	                           			
	        }).then(function(response) {
	                           			$scope.edit1 = angular.copy(response.data);
	                           			 
	                           		});
	                           	    
	                           	    
	                     		
	    $scope.editShipping = function(){	
				
	  		 $http({
	  				method: 'POST',
	  				url : '/editshippingaddress',
	  				data : $scope.edit1,
	  			
	  			}).then(function(response){
	  				if(response.data.status){
	  					alert('Address edit Successfully!');
	  					$scope.edit1= {};
	  					
	  				} else {
	  					alert('Address edit Failed!');
	  				}
	  			});
	  			};     
			  }]);		
 
  
  
 
 

 
 
 app.controller('editproductctrl',[ '$scope','$route','$routeParams', '$rootScope','$http',
                           	     function($scope,$route,$routeParams,$rootScope, $http)
                           	     {
                           			  
                           	    	$scope.title=' Edit Product!!!';
                            	    
                             	   /*   $scope.product=
                             	  	                {
                             	  	                  "productName": "hhh",
                             	  	                  
                             	  	                
                             	  	                 }  */       
                           	    	
                           	    	
                           	    	$http({
                           	 		method : 'GET',
                           	 		url : '/categories',     
                           	 	
                           	 	}).then(function(response) {
                           	 		$rootScope.categories = response.data;
                           	 		
                           	 	});
                           	    	
                           	    	
                           	    	$http({
                               	 		method : 'GET',
                               	 		url : '/brands',     
                               	 	
                               	 	}).then(function(response) {
                               	 		$rootScope.brands = response.data;
                               	 		
                               	 	});
                           	    	
                           	    	
                           	    	
                             	     $http({
                                  			
                             	   	   method : 'GET',
                             	                              			
                             	                              			
                             	          url : '/products/'+$routeParams.id,
                             	                              			
                             	           }).then(function(response) {
                             	                              			$scope.product = angular.copy(response.data);
                             	                              			 
                             	                              		});
                             	    	 
                             	    	 $scope.editproduct = function(){	
                             				
                             	   		 $http({
                             	   				method: 'POST',
                             	   				url : '/saveproduct',
                             	   				data : $scope.product,
                             	   			
                             	   			}).then(function(response){
                             	   				if(response.data.status){
                             	   					alert('product edit Successfully!');
                             	   					//$scope.edit= {};
                             	   					
                             	   				} else {
                             	   					alert('product edit Failed!');
                             	   				}
                             	   			});
                             	   			};   
                             	      }]);
 
 
 app.controller('addproductctrl', [ '$scope','fileUpload','$route','$routeParams', '$rootScope','$http',
                          	     function($scope,fileUpload,$route,$routeParams,$rootScope, $http){
    
	
	 
	 $http({
			method : 'GET',
			url : '/categories',     
		
		}).then(function(response) {
			$rootScope.categories = response.data;
			
	})
	
	$http({
			method : 'GET',
			url : '/brands',     
		
		}).then(function(response) {
			$rootScope.brands = response.data;
			
	})

	
	 $scope.product={
			 /*category:{
				 
			 }
	 
	          brands:{
	         }
			 */
	 
	 }
	/* $scope.categories=[{"categoryName":"shirt"
		 
	 }]*/
	 
		$scope.uploadFile = function(){
	        var file = $scope.myFile;
	        console.log('file is ' );
	        console.dir(file);
	        var uploadUrl = "/saveproduct";
	        fileUpload.uploadFileToUrl(file, uploadUrl);
	        if(fileUpload.uploadFileToUrl(file, uploadUrl)){
	        	alert("Image uploaded successfully");
	        }
		};
		
		$rootScope.loadImageFileAsURL=function(){

		    var filesSelected = document.getElementById("inputFileToLoad").files;
		    if (filesSelected.length > 0){
		        var fileToLoad = filesSelected[0];

		        var fileReader = new FileReader();

		        fileReader.onload = function(fileLoadedEvent) {
		            $rootScope.srcData = fileLoadedEvent.target.result; // <--- data: base64

		            var divTest = document.getElementById("imgTest");
		            var newImage = document.createElement('img');
		            newImage.src = $rootScope.srcData;

//		            divTest.innerHTML = newImage.outerHTML;

		        }

		        fileReader.readAsDataURL(fileToLoad);
		    }
		};
		
		$scope.addCategory=function(){
			 $http({
	  				method: 'POST',
	  				url : '/addcategory',
	  				data : $scope.category,
	  			
	  			}).then(function(response){
	  				if(response.data.status){
	  					alert('Category added Successfully!');
	  					$scope.category= {};
	  					
	  				} else {
	  					alert('Failed!');
	  				}
	  			});
		}
		
		
$scope.addBrand=function(){
	$http({
			method: 'POST',
			url : '/addbrand',
			data : $scope.brand,
		
		}).then(function(response){
			if(response.data.status){
				alert('Brand Added Successfully!');
				$scope.brand= {};
				
			} else {
				alert(' Failed!');
			}
		});
		}


$scope.saveproduct = function(){
	$scope.product.image=$rootScope.srcData;
	$scope.product.extn="jpg";
			
			/*$scope.product={
					"name":"",
					"price":"",
					"quantity":"",
					"extn":"jpg",
					"image":$rootScope.srcData,
					brand:{
						"brandName":""
					},
					category:{
						"categoryName":""
					}
			};*/
debugger;
			$http({
				method: 'POST',
				url : '/saveproduct',
				data : $scope.product
			}).then(function(response){
				if(response.data.status){
					alert('product Added Successfully!');	
												} 
				
				else {
					alert('product Addition Failed!');
				       }
			});
		};
                                                             
 }])
 app.controller('gridctrl',[ '$scope','$route','$routeParams', '$rootScope','$http',
                           	     function($scope,$route,$routeParams,$rootScope, $http)
                           	     {
	
	
	/* $scope.images=[
	                {
	                  "id": "1",
	                  "imageUrl": "4.jpg",
	                  "price":"343534",
	                  "name": "Ara1",
	                 },
	                  {
	                  "id": "2",
	                  "imageUrl": "5.jpg",
	                  "price":"34534",
	                  "name": "Ara2",
	                 }*//*,
	                  {
	                  "id": "3",
	                  "imageUrl": "4.jpg",
	                  "price":"335434",
	                  "name": "Ara3",
	                 },
	                  {
	                  "id": "3",
	                  "imageUrl": "6.jpg",
	                  "price":"335434",
	                  "name": "Ara3",
	                 } ,{
	                  "id": "3",
	                  "imageUrl": "6.jpg",
	                  "price":"335434",
	                  "name": "Ara3",
	                 }, {
	                  "id": "3",
	                  "imageUrl": "6.jpg",
	                  "price":"335434",
	                  "name": "Ara3",
	                 }
	                
	              ]
	               */
	             $scope.title="List Of Products";
	

                          			  
                          			  
/* 
 $http({
			method : 'GET',
			url : '/products',
			
		}).then(function(response) {
			$rootScope.product = angular.copy(response.data);
			 
		});
              */   
                           	     
		$http({
			method : 'GET',
			url : '/products',	
		}).then(function(response) {
		
			$rootScope.products = angular.copy(response.data);		 
		});
	                          			  }]);
 


/*}]);*/
 
 
 app.controller('listctrl', [ '$scope','$route','$routeParams', '$rootScope','$http',
                          	     function($scope,$route,$routeParams,$rootScope, $http){
    
	 
	 $http({
			method : 'GET',
			url : '/products'
		}).then(function(response) {
			$rootScope.products = response.data;
	});
                                                             
 }])
app.controller('cartctrl', 
	[ '$scope','$route','$routeParams', '$rootScope','$http',
	     function($scope,$route,$routeParams,$rootScope, $http)
	     {
  /* $scope.images=[
  {
    "id": "1",
    "imageUrl": "1.jpg",
    "price":"343534",
    "name": "Ara1",
    "quantity":1
   },
    {
    "id": "2",
    "imageUrl": "1.jpg",
    "price":"34534",
    "name": "Ara2",
     "quantity":2
   }
   ]*/
   
   /*$scope.shippingAddresses=[
     {
       "id":"1",
       "address1":"indu aranya",
       "address2":"nagole hyd",
       "city":"hyd",
       "phone":"5555555555",
       "pin":"4545456",
       "state":"telangana"
     },
      {
        "id":"2",
       "address1":"indu aranya",
       "address2":"nagole hyd",
       "city":"hyd",
       "phone":"5555555555",
       "pin":"4545456",
       "state":"telangana"
     },
   ]*/
  /*  $scope.addShipping=function(){
            $scope.shippingAddresses.push($scope.shippingAddress);
             $scope.shippingAddress='';*/
	
	
	

	  $http({
			
		   method : 'GET',
	                           			
	                           			
	       url : '/cart',
	                           			
	        }).then(function(response) {
	                           			$scope.cart = angular.copy(response.data);
	                           			
	                           			debugger;
	                           		});
	  
    
 }])
 
 
 
 
 app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);

/*app.controller('myCtrl', ['$scope', 'fileUpload','$http', function($scope, fileUpload, $http){
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/upload";
        fileUpload.uploadFileToUrl(file, uploadUrl);
        
     
	   
    };
}]);*/
    /*$scope.image = "data:image/"
        + "jpg"
        + ";base64,"
        + "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";



app.controller('displayctrl',[ '$scope', '$rootScope','$http',
	     function($scope,$rootScope, $http)
	     {
	debugger;
	
		   	$scope.itemimages={};
		    $scope.image = "data:image/"
                + $scope.itemimages.extn
                + ";base64,"
                + $scope.itemimages.url;
		   		$http({
		   			method : 'GET',
		   			url : '/display',
		   		}).then(function(response) {
		   			$rootScope.itemimages = response.data;
		});	   
	     }]);
  
  */


app.controller('mainctrl', 
		[ '$scope','$route','$routeParams', '$rootScope','$http',
		     function($scope,$route,$routeParams,$rootScope, $http)
		     {
$http({
	
	   method : 'GET',
                        			
                        			
    url : '/customers/one',
                        			
     }).then(function(response) {
                        			$scope.customer = angular.copy(response.data);
                        			 
                        		});
}]);