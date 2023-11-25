let slideIndex = 1;
showSlides(slideIndex);
formatPrice();
function plusSlides(n) {
    showSlides(slideIndex += n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let currentSlide = document.getElementById("currentSlide");

    if (n > slides.length) {
        slideIndex = 1;
    }

    if (n < 1) {
        slideIndex = slides.length;
    }

    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }

    slides[slideIndex - 1].style.display = "block";
    currentSlide.innerHTML = slideIndex;
}
function formatPrice() {
    $(".item-price").each(function () {
        let itemPrice = $(this).text(); // 현재 요소의 텍스트 가져오기
        let formattedPrice = parseFloat(itemPrice).toLocaleString('en-US') + ' 원';
        $(this).text(formattedPrice); // 형식화된 가격으로 업데이트
    });
}

var quantityInput = document.getElementById('quantityInput');

function decreaseQuantity() {
    var currentQuantity = parseInt(quantityInput.value, 10);
    if (currentQuantity > 1) {
        quantityInput.value = currentQuantity - 1;
    }
}

function increaseQuantity() {
    var currentQuantity = parseInt(quantityInput.value, 10);
    if (currentQuantity < 10) {
        quantityInput.value = currentQuantity + 1;
    }
}

function addToCart() {
    var form = document.getElementById('addToCartForm');
    // 여기에서 수량을 폼에 추가할 수 있습니다.
    form.submit();
}

document.getElementById('addToCartForm').addEventListener('submit', function (event) {
    event.preventDefault();

    alert('장바구니에 상품이 추가되었습니다!');

    this.submit();
});