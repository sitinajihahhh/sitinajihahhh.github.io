
<?php
session_start();

$_SESSION['customer']['name'] = $_POST['name'] ?? '';
$_SESSION['customer']['phone'] = $_POST['phone'] ?? '';
$_SESSION['receipt']['total'] = $_POST['total'] ?? '0.00';
$_SESSION['receipt']['items'] = $_POST['items'] ?? '[]';

$_SESSION['receipt']['payment'] = [
  'method' => $_POST['method'] ?? '',
  'cash' => $_POST['cash'] ?? '',
  'balance' => $_POST['balance'] ?? ''
];

// Redirect or open receipt
header("Location: customer_receipt.php");
exit;