
<?php include 'connect.php'; 

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $item_code = $_POST['item_code'];
    $item_name = $_POST['item_name'];
    $item_price = $_POST['item_price'];
     $qty = (int) $_POST['quantity']; // ✅ matches input name="quantity"

    $total = $_POST['total'];
    $cash = $_POST['cash'];
    $balance = $_POST['balance'];

    $stmt = $conn->prepare("INSERT INTO transaction (item_code, item_name, item_price, quantity, total, cash, balance) VALUES (?, ?, ?, ?, ?, ?, ?)");
    $stmt->bind_param("ssdidds", $item_code, $item_name, $item_price, $qty, $total, $cash, $balance);

    if ($stmt->execute()) {
        echo "<script>alert('✅ Payment recorded successfully!');</script>";
    } else {
        echo "<script>alert('❌ Failed to record payment.');</script>";
    }

    $stmt->close();
	 $update = $conn->prepare("
        UPDATE item 
        SET 
            item_quantitysold = item_quantitysold + ?, 
            item_stock = item_stock - ?
        WHERE item_code = ?
    ");
    $update->bind_param("iis", $qty, $qty, $item_code);

    if ($update->execute()) {
        echo "<script>alert('✅ Payment and stock update successful!');</script>";
    } else {
        echo "<script>alert('❌ Failed to update item stock.');</script>";
    }

    $update->close();
    $conn->close();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>CASHIER PANEL</title>
    <link href="https://fonts.googleapis.com/css?family=Concert+One|Righteous|Yanone+Kaffeesatz" rel="stylesheet">
</head>
<body style="background-image: url(images/back.jpg);">
    <header>
        <nav class="navbar navbar-light fixed-top" style="background-color: #84CEEB;">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                       
                        <span style="font-family: 'Righteous', cursive; text-decoration: underline">
                            <b><span style="color:blue; font-size: 150%">C</span>ASHIER <span style="color: blue; font-size: 150%">P</span>ANEL</b>
                        </span>
                    </a>
                </div> 
                <div>
                    <button class="button" style="box-shadow: 1px 2px 5px #000000;" onclick="window.location.href='staff_login.php'"><span><i class="fa fa-sign-out"></i> Logout </span></button>
                </div>           
            </div>
        </nav>
    </header>

    <div class="row" style="margin-top: 90px;">
        <div class="col-md-2 sidebar" style="text-align:left;">
            <nav class="navbar navbar-dark sidenav">
                <div class="container">
                    <ul class="nav navbar-nav">
					
<button class="btn btn-info" onclick="window.location.href='cashier.php'"><i class="fa fa-user-circle-o" style="font-size:21px"></i> Transactions</button>
                </div>        
            </nav>
        </div>  

        <div class="col-md-10 tab-content content">
            <div id="transactions" class="tab-pane active">
                <div class="row">
                    <div class="col-md-6 container">
					<div class="form-group row position-relative">
  <label class="col-sm-3 col-form-label">Customer ID</label>
  <div class="col-sm-9">
    <input type="text" id="custId" class="form-control" placeholder="Type Customer ID" autocomplete="off">
    <div id="custIdSuggestions" class="list-group" style="position: absolute; z-index: 1000;"></div>
  </div>
</div>

<div class="form-group row">
  <label class="col-sm-3 col-form-label">Name</label>
  <div class="col-sm-9">
    <input type="text" id="name" class="form-control" readonly>
  </div>
</div>

<div class="form-group row">
  <label class="col-sm-3 col-form-label">Phone</label>
  <div class="col-sm-9">
    <input type="text" id="phone" class="form-control" readonly>
  </div>
</div>

                        <form id="transForm" method="POST" action="insert_transaction.php">
								<div class="form-group row">
								<label class="col-sm-3 col-form-label">Category</label>
								<div class="col-sm-9">
									<select class="form-control" id="itemCategory" onchange="loadItemsByCategory(this.value)" required>
  <option value="">Select Category</option>
  <option value="Passport Photo">Passport Photo</option>
  <option value="Photo Printing">Photo Printing</option>
  <option value="Frames">Frames</option>
  <option value="Paper Printing">Paper Printing</option>
  <option value="Photocopy">Photocopy</option>
  <option value="Lamination">Lamination</option>
</select>

								</div>
							</div>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Item Code</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="itemCode" name="itemCode" onchange="syncNameFromCode(this.value)" required>
									<option value="">Select Item Code</option>  
									</select>
                                </div>
                            </div>
                            <!-- ITEM NAME DROPDOWN -->
								<div class="form-group row">
									<label class="col-sm-3 col-form-label">Item Name</label>
									<div class="col-sm-9">
										<select class="form-control" id="itemName" name="itemName" onchange="syncCodeFromName(this.value)" >
										  <option value="">Select Item</option>  
											<!-- Dynamically populated -->
										</select>
									</div>
								</div>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Price</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="itemPrice" name="itemPrice" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Stock</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="stock" name="stock" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Quantity</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" id="quantity" name="quantity" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9">
                                  <button type="submit" class="btn btn-warning" style="width: 50%; float: right; box-shadow: 0px 0px 10px 0px #000;">Add Item</button>

                                </div>
                            </div>
                        </form>

                        <form id="billForm" method="POST" action="calculate_bill.php">
						<div class="form-group row">
      <label class="col-sm-3 col-form-label">Payment Method</label>
      <div class="col-sm-9">
        <select class="form-control" id="paymentMethod" name="paymentMethod">
          <option value="">Select Payment Method</option>
          <option value="cash">Cash</option>
          <option value="qr">QR Code</option>
        </select>
      </div>
    </div>
	 
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Total Bill</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="totalBill" name="totalBill" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Cash</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="cash" name="cash">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Balance</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="balance" name="balance" readonly>
                                </div>
                            </div>
							  <div id="qrImage" style="display: none; text-align:center; padding:10px;">
    <img src="images/qr_code.jpeg" alt="QR Code" width="400">

    </div>
                            <div class="form-group row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9">
<button type="button" onclick="generateBillAndShowReceipt()" class="btn btn-dark" style="width: 50%; float: right; box-shadow: 0px 0px 10px 0px #000;">
  Generate Bill
</button>


                                    <button type="button" onclick="window.location.reload();" class="btn btn-warning" style="width: 50%; float: right; margin-right: 10px; box-shadow: 0px 0px 10px 0px #000;">Finish</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-md-6">
<table class="table table-striped table-bordered text-right" style="background-color: white;">

  <thead class="thead-dark">
  <tr>
    <th scope="col">Code</th>
    <th scope="col">Name</th>
    <th scope="col">Price</th>
    <th scope="col">Quantity</th>
    <th scope="col">Total</th>
  </tr>
</thead>

<tbody id="table_bdy"></tbody>

</table>


                    </div>
                </div>
            </div>
    <footer>
        <div class="footer bottom text-center mt-4">
            <p class="f1">©2025 - SUPERMARKET SYSTEM</p>
            <p class="f2">ALL RIGHTS RESERVED</p>
       
        </div>
    </footer>

    <!-- Required Scripts -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	

    <script>
	let itemMap = {}; // code → name
let reverseMap = {}; // name → code
document.getElementById('custId').addEventListener('input', function () {
  const search = this.value.trim();
  if (search.length >= 1) {
    fetch(`search_customeR.php?term=${encodeURIComponent(search)}`)
      .then(res => res.json())
      .then(data => {
        const suggestions = document.getElementById('custIdSuggestions');
        suggestions.innerHTML = '';
        data.forEach(cust => {
          const item = document.createElement('a');
          item.href = "#";
          item.classList.add('list-group-item', 'list-group-item-action');
          item.textContent = `${cust.cust_id} - ${cust.cust_name}`;
          item.onclick = () => {
            document.getElementById('custId').value = cust.cust_id;
            suggestions.innerHTML = '';
            fetchCustomerDetails(); // Fetch name & phone
          };
          suggestions.appendChild(item);
        });
      });
  } else {
    document.getElementById('custIdSuggestions').innerHTML = '';
  }
});

function fetchCustomerDetails() {
  const custId = document.getElementById('custId').value.trim();
  if (!custId) return;

  fetch(`get_customer.php?id=${encodeURIComponent(custId)}`)
    .then(res => res.json())
    .then(data => {
      if (data.error) {
        alert(data.error);
        document.getElementById('name').value = '';
        document.getElementById('phone').value = '';
      } else {
        document.getElementById('name').value = data.cust_name;
        document.getElementById('phone').value = data.cust_phonenum;
      }
    });
}

function fetchCustomerDetails() {
  const custId = document.getElementById('custId').value.trim();
  if (!custId) return;

  fetch(`get_customer.php?id=${encodeURIComponent(custId)}`)
    .then(response => response.json())
    .then(data => {
      if (data.error) {
        alert(data.error);
        document.getElementById('name').value = '';
        document.getElementById('phone').value = '';
      } else {
        document.getElementById('name').value = data.cust_name;
        document.getElementById('phone').value = data.cust_phonenum;
      }
    })
    .catch(err => {
      console.error('Error fetching customer:', err);
    });
}

function loadItemsByCategory(category) {
  fetch('get_item.php?category=' + encodeURIComponent(category))
    .then(response => response.json())
    .then(items => {
      const itemCodeSelect = document.getElementById('itemCode');
      const itemNameSelect = document.getElementById('itemName');
      itemCodeSelect.innerHTML = '<option value="">Select Item Code</option>';
      itemNameSelect.innerHTML = '<option value="">Select Item Name</option>';

      itemMap = {};
      reverseMap = {};

      items.forEach(item => {
        itemMap[item.item_code] = item.item_name;
        reverseMap[item.item_name] = item.item_code;

        const optCode = document.createElement('option');
        optCode.value = item.item_code;
        optCode.textContent = item.item_code;
        itemCodeSelect.appendChild(optCode);

        const optName = document.createElement('option');
        optName.value = item.item_name;
        optName.textContent = item.item_name;
        itemNameSelect.appendChild(optName);
      });
    });
}

function syncNameFromCode(code) {
  const name = itemMap[code] || '';
  const nameSelect = document.getElementById('itemName');
  Array.from(nameSelect.options).forEach(option => {
    option.selected = option.value === name;
  });
  if (code) loadItemDetails(code);
}

function syncCodeFromName(name) {
  const code = reverseMap[name] || '';
  const codeSelect = document.getElementById('itemCode');
  Array.from(codeSelect.options).forEach(option => {
    option.selected = option.value === code;
  });
  if (code) loadItemDetails(code);
}

function loadItemDetails(code) {
  fetch('get_item.php?code=' + encodeURIComponent(code))
    .then(res => res.json())
    .then(data => {
      if (data) {
        document.getElementById('itemPrice').value = data.price;
        document.getElementById('stock').value = data.stock;

        const qtyInput = document.getElementById('quantity');
        qtyInput.disabled = false;
        qtyInput.min = 1;
        qtyInput.max = data.stock;
        qtyInput.value = 1;
        qtyInput.placeholder = `1 to ${data.stock}`;
      } else {
        resetItemDetails();
      }
    })
    .catch(err => {
      console.error('Failed to load item details:', err);
      resetItemDetails();
    });
}

function resetItemDetails() {
  document.getElementById('itemPrice').value = '';
  document.getElementById('stock').value = '';
  const qtyInput = document.getElementById('quantity');
  qtyInput.disabled = true;
  qtyInput.value = '';
  qtyInput.placeholder = 'Select valid item';
}

document.getElementById('transForm').addEventListener('submit', function (event) {
  event.preventDefault();

  const quantity = parseInt(document.getElementById('quantity').value);
  const stock = parseInt(document.getElementById('stock').value);
  const price = parseFloat(document.getElementById('itemPrice').value);
  const code = document.getElementById('itemCode').value;
  const name = document.getElementById('itemName').selectedOptions[0]?.textContent || '';

  if (!quantity || quantity > stock) {
    alert('Invalid quantity or exceeds stock.');
    return;
  }

 const total = price * quantity;
const row = document.createElement('tr');
row.innerHTML = `
  <td>${code}</td>
  <td>${name}</td>
  <td>${price.toFixed(2)}</td>
  <td>${quantity}</td>
  <td>${total.toFixed(2)}</td>
`;

  document.getElementById('table_bdy').appendChild(row);

  updateTotalBill();
  this.reset();
});

function updateTotalBill() {
  let total = 0;
  document.querySelectorAll('#table_bdy tr').forEach(row => {
    const price = parseFloat(row.cells[2].textContent);
    const qty = parseInt(row.cells[3].textContent);
    total += price * qty;
  });
  document.getElementById('totalBill').value = total.toFixed(2);
}

document.getElementById('cash').addEventListener('input', function () {
  const cash = parseFloat(this.value) || 0;
  const total = parseFloat(document.getElementById('totalBill').value) || 0;
  document.getElementById('balance').value = (cash - total).toFixed(2);
});

document.getElementById('paymentMethod').addEventListener('change', function () {
  const cashFields = document.getElementById('cashFields');
const qrImage = document.getElementById('qrImage');

if (this.value === 'cash') {
  if (cashFields) cashFields.style.display = 'block';
  if (qrImage) qrImage.style.display = 'none';
} else if (this.value === 'qr') {
  if (cashFields) cashFields.style.display = 'none';
  if (qrImage) qrImage.style.display = 'block';
} else {
  if (cashFields) cashFields.style.display = 'none';
  if (qrImage) qrImage.style.display = 'none';
}

});

document.getElementById('billForm').addEventListener('submit', function(e) {
  e.preventDefault();

  const rows = document.querySelectorAll('#table_bdy tr');
  const items = [];
  rows.forEach(function(row) {
    const cells = row.children;
    items.push({
      code: cells[0].textContent,
      name: cells[1].textContent,
      price: parseFloat(cells[2].textContent),
      quantity: parseInt(cells[3].textContent)
    });
  });

  const form = document.createElement('form');
  form.method = 'POST';
  form.action = 'set_receipt.php';

  const itemsInput = document.createElement('input');
  itemsInput.type = 'hidden';
  itemsInput.name = 'items';
  itemsInput.value = JSON.stringify(items);
  form.appendChild(itemsInput);

  const totalInput = document.createElement('input');
  totalInput.type = 'hidden';
  totalInput.name = 'total';
  totalInput.value = document.getElementById('totalBill').value;
  form.appendChild(totalInput);

  document.body.appendChild(form);
  form.submit();
});

function openReceipt() {
  const rows = document.querySelectorAll('#table_bdy tr');
  const items = [];

  rows.forEach(function(row) {
    const cells = row.children;
    items.push({
      code: cells[0].textContent,
      name: cells[1].textContent,
      price: parseFloat(cells[2].textContent),
      quantity: parseInt(cells[3].textContent),
      total: parseFloat(cells[4].textContent)
    });
  });

  const total = document.getElementById('totalBill').value;

  const form = document.createElement('form');
  form.method = 'POST';
  form.action = 'receipt.php';
  form.target = '_blank'; // Open in new tab
  form.style.display = 'none';

  const itemsInput = document.createElement('input');
  itemsInput.name = 'items';
  itemsInput.value = JSON.stringify(items);
  form.appendChild(itemsInput);

  const totalInput = document.createElement('input');
  totalInput.name = 'total';
  totalInput.value = total;
  form.appendChild(totalInput);

  document.body.appendChild(form);
  form.submit();
}
        window.onload = function () {
            fetch('get_item.php')
                .then(response => response.json())
                .then(data => {
                    const dropdown = document.getElementById('itemCode');
                    data.forEach(item => {
                        // let opt = document.createElement('option');
                        opt.value = item.code;
                        opt.text = item.code;
                        dropdown.appendChild(opt);
                    });
                });
        };
// At the end of generateBillAndShowReceipt()
function generateBillAndShowReceipt() {
  const customerName = document.getElementById('name')?.value || '';
  const customerPhone = document.getElementById('phone')?.value || '';
  const totalBill = document.getElementById('totalBill').value;
   const paymentMethod = document.getElementById('paymentMethod').value;
  const cash = document.getElementById('cash').value || '';
  const balance = document.getElementById('balance').value || '';

  const rows = document.querySelectorAll('#table_bdy tr');
  const items = [];

  rows.forEach(row => {
    const cells = row.children;
    items.push({
      code: cells[0].textContent,
      name: cells[1].textContent,
      price: parseFloat(cells[2].textContent),
      quantity: parseInt(cells[3].textContent),
      total: parseFloat(cells[4].textContent)
    });
  });
  function generateBillAndShowReceipt() {
  const custId = document.getElementById('custId')?.value || '';
  const staffId = 'STAFF001'; // Set this dynamically from session or hidden input
  const totalBill = document.getElementById('totalBill').value;

  fetch('store_receipt.php', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: new URLSearchParams({
      cust_id: custId,
      staff_id: staffId,
      total: totalBill
    })
  }).then(res => res.json())
    .then(res => {
      if (res.success) {
        console.log('Receipt No:', res.receipt_no);
        window.open('customer_receipt.php', '_blank');
      } else {
        alert('❌ Failed to store receipt: ' + res.error);
      }
    });
}

   // ✅ Store only current receipt
  const receiptData = {
    name: customerName,
    phone: customerPhone,
    total: totalBill,
    items: items,
    payment: {
      method: paymentMethod,
      cash: cash,
      balance: balance
    }
  };
    localStorage.setItem('receiptData', JSON.stringify(receiptData));
  window.open('customer_receipt.php', '_blank');
    localStorage.removeItem('receiptData');
  localStorage.setItem('receiptData', JSON.stringify(receiptData));
 const form = document.createElement('form');
  form.method = 'POST';
  form.action = 'set_receipt.php';
  form.target = '_blank'; // open receipt in new tab

  const fields = {
    name: customerName,
    phone: customerPhone,
    total: totalBill,
    items: JSON.stringify(items),
    method: paymentMethod,
    cash: cash,
    balance: balance
  };

  for (let key in fields) {
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = key;
    input.value = fields[key];
    form.appendChild(input);
  }

  document.body.appendChild(form);
  form.submit();
}
  // Save to localStorage or send to PHP
  localStorage.setItem('receiptData', JSON.stringify({
    name: customerName,
    phone: customerPhone,
    total: totalBill,
     items,
    payment: {
      method: paymentMethod,
      cash: cash,
      balance: balance
    }
  }));

  // Open receipt page
  window.open('customer_receipt.php', '_blank');

    </script>
</body>
</html>