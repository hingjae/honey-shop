function formatPrice() {
    $(".item-price").each(function () {
        let itemPrice = $(this).text(); // 현재 요소의 텍스트 가져오기
        let formattedPrice = parseFloat(itemPrice).toLocaleString('en-US') + ' 원';
        $(this).text(formattedPrice); // 형식화된 가격으로 업데이트
    });
}

// 모든 행의 totalPrice 값을 더하여 총합을 계산하는 함수
function calculateTotalPrice() {
    var total = 0;

    // 각 행의 totalPrice 값을 가져와서 더함
    $('.item-total-price').each(function () {
        var priceWithComma = $(this).text(); // 예: "15,000 원"

        // 쉼표를 제거하고 정수로 변환
        var price = parseInt(priceWithComma.replace(/,/g, ''));

        // 여기서 price를 사용하여 필요한 작업을 수행할 수 있습니다.

        total += isNaN(price) ? 0 : price;
    });

    return total;
}

function formatInputPrice(price) {
    // 숫자를 3자리 단위로 쉼표 추가
    var formattedNumber = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    // '원' 추가
    formattedNumber += ' 원';

    return formattedNumber;
}

function removeCartItem() {
    $('.remove-btn').click(function () {
        //해당 빼기 버튼이 속한 행을 찾음
        let row = $(this).closest('tr');
        let cartItemId = row.find('input[type="hidden"]').val();

        $.ajax({
            url: '/api/cartItems',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify({ cartItemId: cartItemId}),
            success: function () {
                row.remove();

                let totalPriceSum = calculateTotalPrice();
                let formattedTotalPriceSum = formatInputPrice(totalPriceSum);
                $('#total-price-sum').text(formattedTotalPriceSum);
            },
            error: function (error) {
                console.log('Error: ', error);
            },
        })
    })
}

$(document).ready(function () {
    formatPrice();
    removeCartItem();
})

