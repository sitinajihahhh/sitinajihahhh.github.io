<?php
session_start();


$customerName = $_SESSION['customer']['name'] ?? '';
$customerPhone = $_SESSION['customer']['phone'] ?? '';
$items = json_decode($_SESSION['receipt']['items'] ?? '[]', true);
$total = $_SESSION['receipt']['total'] ?? '0.00';
?>

<!DOCTYPE html>
<html>
<head>
  <title>Customer Receipt</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <style>
    .receipt {
      max-width: 600px;
      margin: 50px auto;
      padding: 30px;
      border: 1px solid #ccc;
      background-color: #fff;
    }
    h3 { text-align: center; }
  </style>
</head>
<body>
  <div class="receipt">
    <h3>Customer Receipt</h3>
    <div id="customerInfo"></div>
    <hr>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Code</th>
          <th>Name</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody id="receiptItems"></tbody>
    </table>
    <h5 class="text-right">Total: RM <span id="receiptTotal">0.00</span></h5>
	 <div id="extraPaymentInfo" class="text-right mt-2"></div>
  </div>
  <script>
    const data = JSON.parse(localStorage.getItem('receiptData'));
    if (data) {
     let customerHTML = `
  <p><strong>Name:</strong> ${data.name}</p>
  <p><strong>Phone:</strong> ${data.phone}</p>
`;
if (data.payment) {
  const cash = parseFloat(data.payment.cash || 0).toFixed(2);
  const balance = parseFloat(data.payment.balance || 0).toFixed(2);
  document.getElementById('extraPaymentInfo').innerHTML = `
    <p><strong>Cash:</strong> RM ${cash}</p>
    <p><strong>Balance:</strong> RM ${balance}</p>
  `;
}


document.getElementById('customerInfo').innerHTML = customerHTML;

      let total = 0;
      data.items.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${item.code}</td>
          <td>${item.name}</td>
          <td>${item.price.toFixed(2)}</td>
          <td>${item.quantity}</td>
          <td>${(item.price * item.quantity).toFixed(2)}</td>
        `;
        document.getElementById('receiptItems').appendChild(row);
        total += item.price * item.quantity;
      });

      document.getElementById('receiptTotal').textContent = total.toFixed(2);
    }
	
  </script>
</body>
</html>