CREATE OR REPLACE
    ALGORITHM = UNDEFINED
VIEW stock AS
    SELECT
		UUID() as 'id',
        warehouse.id as warehouse_id,
		warehouse.description as warehouse_description,
        shelf.id as shelf_id,
        shelf.code as shelf_code,
        product.id as product_id,
		product.code as product_code,
		SUM(CASE
			WHEN ware_transaction.ware_transaction_type = 'IMPORT' THEN ware_transaction_detail.quantity * 1
			ELSE ware_transaction_detail.quantity * - 1
		END) AS total_quantity
    FROM ware_transaction_detail
	JOIN ware_transaction on ware_transaction_detail.ware_transaction_id = ware_transaction.id
	JOIN product on ware_transaction_detail.product_id = product.id
	JOIN shelf on ware_transaction_detail.shelf_id = shelf.id
	JOIN warehouse on shelf.warehouse_id = warehouse.id
    GROUP BY warehouse_id, warehouse_description, shelf_id, shelf_code, product_id, product_code;