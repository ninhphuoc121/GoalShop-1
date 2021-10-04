const app = angular.module("shopping-cart-app", []);
jQuery(document).ready(function($) {
	$('.bar-menu').click(function(event) {
		$(this).next('.list-child').slideToggle(300);
	});
});
app.controller("shopping-cart-ctrl", function($scope, $http) {

    $scope.cart = {
        items: [],
        prod : [] ,  
        add(product_id) {
            var item = this.items.find(item => (item.product_id == product_id ));

            if (item) {
                item.quantity++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${product_id}`).then(resp => {
                    resp.data.quantity = 1;
             
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        }
        ,
        maxquantity(product_id){
        	 $http.get(`/rest/products/${product_id}`).then(resp => {
                    this.prod = resp.data;
                })
        	return this.prod.quantity;
        }
        ,
        remove(product_id) {
            var index = this.items.findIndex(item => item.product_id == product_id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        amt_of(item) {},
        get count() {
            return this.items
                .map(item => item.quantity)
                .reduce((total, quantity) => total += quantity, 0);
        },
        get amount() {
            return this.items
                .map(item => item.quantity*item.unit_price-((item.quantity*item.unit_price)*item.distcount/100))
                .reduce((total, quantity) => total += quantity, 0);
        },
        get amount1() {
            return this.items
                .map(item => item.quantity * item.unit_price)
                .reduce((total, quantity) => total += quantity, 0);
        },
         get amount2() {
            return this.items
                .map(item => (item.quantity * item.unit_price)-(item.quantity*item.unit_price-((item.quantity*item.unit_price)*item.distcount/100)))
                .reduce((total, quantity) => total += quantity, 0);
        },
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
            
        },
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
        
    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createDate: new Date(),
        address: "",
        phone : "",
        status : 0,
        account: { username: $("#username").text()},
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { product_id: item.product_id },
                    price: item.unit_price,
                    quantity: item.quantity
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            $http.post("/rest/orders", order).then(resp => {
                alert("dat hang thanh cong");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.order_id;
            }).catch(error => {
                alert("dat hang loi");
                console.log(error)
            })
        }
    }
      
    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.cart.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1 * $scope.cart.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
    
    
})