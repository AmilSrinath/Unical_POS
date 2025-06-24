
CREATE DATABASE unical_pos_test;

use unical_pos_test;

create table if not exists pos_courier_company_tb
(
    company_id      int auto_increment
        primary key,
    company_name    varchar(100) null,
    company_contact varchar(15)  null,
    address         varchar(200) null,
    email           varchar(100) null,
    created_date    datetime     null,
    edited_date     datetime     null,
    status          int          null,
    user_id         int          null
);

create table if not exists pos_courier_branch_tb
(
    branch_id      int auto_increment
        primary key,
    branch_name    varchar(100) null,
    branch_contact varchar(50)  null,
    created_date   datetime     null,
    edited_date    datetime     null,
    company_id     int          null,
    status         int          null,
    user_id        int          null,
    constraint pos_courier_branch_tb_pos_courier_company_tb_company_id_fk
        foreign key (company_id) references pos_courier_company_tb (company_id)
);

create table if not exists pos_emp_employee_designation_tb
(
    designation_id int auto_increment
        primary key,
    designation    varchar(100) null,
    status         int          null,
    user_id        int          null,
    visible        int          null
);

create table if not exists pos_emp_employee_management_tb
(
    employee_id          int auto_increment
        primary key,
    employee_title       varchar(100) null,
    employee_name        varchar(500) null,
    employee_designation varchar(100) null,
    employee_prefix      varchar(100) null,
    employee_code        int          null,
    employee_code_prefix varchar(100) null,
    image_path           text         null,
    phone                varchar(50)  null,
    gmail                varchar(100) null,
    addree               varchar(500) null,
    status               int          null,
    user_id              int          null,
    visible              int          null
);

create table if not exists pos_emp_employee_title_tb
(
    title_id   int auto_increment
        primary key,
    title_name varchar(100) null,
    status     int          null,
    user_id    int          null,
    visible    int          null
);

create table if not exists pos_main_user_role_tb
(
    role_id int auto_increment
        primary key,
    role    varchar(100) null,
    status  int(1)       null,
    user_id int          null,
    visible int          null
);

create table if not exists pos_main_user_tb
(
    user_id     int auto_increment
        primary key,
    employee_id int(100)     null,
    role_id     int(100)     null,
    username    varchar(100) null,
    password    varchar(100) null,
    status      int(10)      null,
    visible     int          null,
    token       varchar(100) null,
    constraint pos_main_user_tb_ibfk_1
        foreign key (employee_id) references pos_emp_employee_management_tb (employee_id),
    constraint pos_main_user_tb_ibfk_2
        foreign key (role_id) references pos_main_user_role_tb (role_id)
);

create table if not exists pos_con_main_table_location_tb
(
    main_table_location_id int auto_increment
        primary key,
    main_location_name     varchar(100) null,
    image_path             text         null,
    status                 int(1)       null,
    user_id                int          null,
    visible                int          null,
    constraint pos_con_main_table_location_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_con_main_table_location_tb (user_id);

create table if not exists pos_con_sub_table_location_tb
(
    sub_table_location_id  int auto_increment
        primary key,
    main_table_location_id int          null,
    main_location_name     varchar(100) null,
    sub_location_name      varchar(100) null,
    image_path             text         null,
    status                 int(1)       null,
    user_id                int          null,
    visible                int          null,
    constraint pos_con_sub_table_location_tb_ibfk_1
        foreign key (main_table_location_id) references pos_con_main_table_location_tb (main_table_location_id),
    constraint pos_con_sub_table_location_tb_ibfk_2
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_con_config_table_location_tb
(
    config_table_location_id int auto_increment
        primary key,
    main_table_location_id   int          null,
    sub_table_location_id    int          null,
    main_location_name       varchar(100) null,
    sub_location_name        varchar(100) null,
    table_prefix             varchar(100) null,
    no_of_tables             int          null,
    image_path               text         null,
    status                   int(1)       null,
    user_id                  int          null,
    visible                  int          null,
    constraint pos_con_config_table_location_tb_ibfk_1
        foreign key (main_table_location_id) references pos_con_main_table_location_tb (main_table_location_id),
    constraint pos_con_config_table_location_tb_ibfk_2
        foreign key (sub_table_location_id) references pos_con_sub_table_location_tb (sub_table_location_id),
    constraint pos_con_config_table_location_tb_ibfk_3
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_con_config_table_details_tb
(
    table_id               int auto_increment
        primary key,
    config_table_id        int          null,
    main_table_location_id int          null,
    sub_table_location_id  int          null,
    table_name             varchar(100) null,
    width                  int          null,
    height                 int          null,
    image_path             text         null,
    status                 int(1)       null,
    user_id                int          null,
    visible                int          null,
    constraint pos_con_config_table_details_tb_ibfk_1
        foreign key (config_table_id) references pos_con_config_table_location_tb (config_table_location_id),
    constraint pos_con_config_table_details_tb_ibfk_2
        foreign key (main_table_location_id) references pos_con_main_table_location_tb (main_table_location_id),
    constraint pos_con_config_table_details_tb_ibfk_3
        foreign key (sub_table_location_id) references pos_con_sub_table_location_tb (sub_table_location_id),
    constraint pos_con_config_table_details_tb_ibfk_4
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index config_table_id
    on pos_con_config_table_details_tb (config_table_id);

create index main_table_location_id
    on pos_con_config_table_details_tb (main_table_location_id);

create index sub_table_location_id
    on pos_con_config_table_details_tb (sub_table_location_id);

create index user_id
    on pos_con_config_table_details_tb (user_id);

create index main_table_location_id
    on pos_con_config_table_location_tb (main_table_location_id);

create index sub_table_location_id
    on pos_con_config_table_location_tb (sub_table_location_id);

create index user_id
    on pos_con_config_table_location_tb (user_id);

create index main_table_location_id
    on pos_con_sub_table_location_tb (main_table_location_id);

create index user_id
    on pos_con_sub_table_location_tb (user_id);

create table if not exists pos_config_reson_tb
(
    reson_id     int auto_increment
        primary key,
    reson        text     not null,
    status       int      null,
    created_date datetime null,
    edited_date  datetime null,
    user_id      int      null,
    constraint pos_config_reson_tb_pos_main_user_tb_user_id_fk
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_inv_stock_category_tb
(
    stock_category_id int auto_increment
        primary key,
    stock_name        varchar(100) null,
    location          varchar(100) null,
    status            int(1)       null,
    user_id           int          null,
    visible           int          null,
    constraint pos_inv_stock_category_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_inv_stock_category_tb (user_id);

create table if not exists pos_inv_supplier_tb
(
    supplier_id   int auto_increment
        primary key,
    salesman_name varchar(100) null,
    company_name  varchar(100) null,
    brand_name    varchar(100) null,
    telephone     varchar(50)  null,
    phone         varchar(50)  null,
    addree        varchar(100) null,
    gmail         varchar(100) null,
    status        int(1)       null,
    user_id       int          null,
    visible       int          null,
    constraint pos_inv_supplier_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_inv_grn_tb
(
    grn_id         int auto_increment
        primary key,
    invoice_no     varchar(100)  null,
    supplier_id    int           null,
    total_price    double(10, 2) null,
    total_discount double(10, 2) null,
    created_Date   date          null,
    status         int(1)        null,
    user_id        int           null,
    visible        int           null,
    constraint pos_inv_grn_tb_ibfk_1
        foreign key (supplier_id) references pos_inv_supplier_tb (supplier_id),
    constraint pos_inv_grn_tb_ibfk_2
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index supplier_id
    on pos_inv_grn_tb (supplier_id);

create index user_id
    on pos_inv_grn_tb (user_id);

create table if not exists pos_inv_purchase_order_tb
(
    po_id          int auto_increment
        primary key,
    po_prefix      varchar(100)   null,
    po_code        int            null,
    po_code_prefix varchar(100)   null,
    supplier_id    int            null,
    supplier_name  varchar(100)   null,
    po_date        date           null,
    expected_date  date           null,
    total_price    decimal(10, 2) null,
    payment_type   int            null,
    status         int            null,
    user_id        int            null,
    visible        int            null,
    constraint pos_inv_purchase_order_tb_ibfk_1
        foreign key (supplier_id) references pos_inv_supplier_tb (supplier_id),
    constraint pos_inv_purchase_order_tb_ibfk_2
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index supplier_id
    on pos_inv_purchase_order_tb (supplier_id);

create index user_id
    on pos_inv_purchase_order_tb (user_id);

create index user_id
    on pos_inv_supplier_tb (user_id);

create table if not exists pos_main_customer_tb
(
    customer_id     int auto_increment
        primary key,
    customer_name   varchar(100)                          null,
    nic             varchar(10)                           null,
    address         varchar(500)                          null,
    phone_one       varchar(13)                           not null,
    phone_two       varchar(13) default 'None'            null,
    created_Date    timestamp   default CURRENT_TIMESTAMP null,
    is_loyalty      int                                   null,
    loyalty_amount  decimal(10, 2)                        null,
    status          int                                   null,
    user_id         int                                   null,
    visible         int                                   null,
    customer_number varchar(10)                           null,
    constraint phone_one
        unique (phone_one),
    constraint pos_main_customer_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_inquiry_tb
(
    inquiry_id       int auto_increment
        primary key,
    way_bill         varchar(20)  not null,
    customer_id      int          not null,
    customer_name    varchar(100) null,
    customer_phone_1 varchar(15)  null,
    customer_phone_2 varchar(15)  not null,
    company          varchar(50)  null,
    branch           varchar(100) null,
    branch_contact   varchar(50)  null,
    reson            varchar(200) null,
    remark           varchar(200) null,
    status           int          null,
    created_date     date         null,
    edited_date      date         null,
    user_id          int          null,
    constraint way_bill
        unique (way_bill),
    constraint pos_inquiry_tb_pos_main_customer_tb_customer_id_fk
        foreign key (customer_id) references pos_main_customer_tb (customer_id),
    constraint pos_inquiry_tb_pos_main_user_tb_user_id_fk
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_main_customer_tb (user_id);

create table if not exists pos_main_discount_tb
(
    discount_id   int auto_increment
        primary key,
    discount_name varchar(100)  null,
    percentage    double(10, 2) null,
    amount        double(10, 2) null,
    status        int(1)        null,
    user_id       int           null,
    visible       int           null,
    constraint pos_main_discount_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_main_discount_tb (user_id);

create table if not exists pos_main_item_category_tb
(
    main_item_category_id   int auto_increment
        primary key,
    main_item_category_name varchar(100)                        null,
    image_path              text                                null,
    status                  int(1)                              null,
    user_id                 int                                 null,
    visible                 int                                 null,
    created_Date            timestamp default CURRENT_TIMESTAMP null,
    edited_by               int                                 null,
    edited_date             timestamp default CURRENT_TIMESTAMP null,
    constraint pos_main_item_category_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_main_item_category_tb (user_id);

create table if not exists pos_main_payment_types_tb
(
    payment_type_id int auto_increment
        primary key,
    payment_type    varchar(100) null,
    status          int(1)       null,
    user_id         int          null,
    visible         int          null,
    constraint pos_main_payment_types_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_main_payment_types_tb (user_id);

create table if not exists pos_main_printer_type_tb
(
    printer_type_id int auto_increment
        primary key,
    printer_type    varchar(100) null,
    status          int(1)       null,
    user_id         int          null,
    visible         int          null,
    constraint pos_main_printer_type_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_main_printer_type_tb (user_id);

create table if not exists pos_main_unit_type_tb
(
    unit_type_id int auto_increment
        primary key,
    unit_type    varchar(100) null,
    status       int(1)       null,
    user_id      int          null,
    visible      int          null,
    constraint pos_main_unit_type_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index user_id
    on pos_main_unit_type_tb (user_id);

create index employee_id
    on pos_main_user_tb (employee_id);

create index role_id
    on pos_main_user_tb (role_id);

create table if not exists pos_module_tb
(
    module_id    int auto_increment
        primary key,
    module_name  varchar(200) not null,
    created_date datetime     null,
    edited_date  datetime     null,
    status       int          null
);

create table if not exists pos_module_wise_tb
(
    module_wise_id int auto_increment
        primary key,
    module_id      int      not null,
    user_id        int      not null,
    created_date   datetime null,
    edited_date    datetime null,
    status         int      null,
    constraint pos_module_wise_tb_pos_main_user_tb_user_id_fk
        foreign key (user_id) references pos_main_user_tb (user_id),
    constraint pos_module_wise_tb_pos_module_tb_module_id_fk
        foreign key (module_id) references pos_module_tb (module_id)
);

create table if not exists pos_status_reg
(
    reg_id      int auto_increment
        primary key,
    description varchar(200) not null,
    create_date datetime     null,
    edited_date datetime     null,
    status      int          null,
    user_id     int          null
);

create table if not exists pos_status_types
(
    status_id   int auto_increment
        primary key,
    status_type varchar(500) null,
    reg_id      int          null,
    user_id     int          null,
    create_date datetime     null,
    edited_date datetime     null,
    status      int          null,
    constraint pos_status_types_pos_status_reg_reg_id_fk
        foreign key (reg_id) references pos_status_reg (reg_id)
);

create table if not exists pos_main_delivery_order_tb
(
    delivery_id      int auto_increment
        primary key,
    customer_id      int                                 not null,
    order_code       varchar(100)                        null,
    cod_amount       decimal(10, 2)                      null,
    weight           varchar(10)                         null,
    remark           varchar(500)                        null,
    status           int(1)    default 1                 null,
    status_id        int       default 2                 null,
    is_free_delivery int       default 0                 null,
    is_return        int       default 0                 null,
    user_id          int                                 null,
    created_date     timestamp default CURRENT_TIMESTAMP null,
    is_exchange      int                                 null,
    constraint pos_main_delicery_order_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id),
    constraint pos_main_delicery_order_tb_ibfk_2
        foreign key (customer_id) references pos_main_customer_tb (customer_id),
    constraint pos_status_types
        foreign key (status_id) references pos_status_types (status_id)
);

create table if not exists pos_main_order_tb
(
    order_id             int auto_increment
        primary key,
    customer_id          int                                 null,
    delivery_order_id    int                                 null,
    bill_no              varchar(100)                        null,
    discount_id          int                                 null,
    sub_total_price      double(10, 2)                       null,
    total_discount_price double(10, 2)                       null,
    delivery_fee         double(10, 2)                       null,
    total_order_price    double(10, 2)                       null,
    payment_type_id      int                                 null,
    table_id             int                                 null,
    created_Date         timestamp default CURRENT_TIMESTAMP null,
    edited_Date          timestamp default CURRENT_TIMESTAMP null,
    adult                int                                 null,
    child                int                                 null,
    remark               varchar(500)                        null,
    user_id              int                                 null,
    edited_by            int                                 null,
    status               int(1)                              null,
    visible              int(1)                              null,
    is_print             int(1)    default 1                 null,
    paid_amount          double                              null,
    constraint pos_main_order_tb_ibfk_1
        foreign key (user_id) references pos_main_user_tb (user_id),
    constraint pos_main_order_tb_ibfk_2
        foreign key (customer_id) references pos_main_customer_tb (customer_id),
    constraint pos_main_order_tb_ibfk_3
        foreign key (delivery_order_id) references pos_main_delivery_order_tb (delivery_id)
            on update cascade on delete cascade
);

create index bill_no_idx
    on pos_main_order_tb (bill_no);

create index user_id
    on pos_main_order_tb (user_id);

create table if not exists pos_payment_tb
(
    payment_id     int auto_increment
        primary key,
    order_id       int           null,
    customer_id    int           null,
    cod            double(10, 2) null,
    total_amount   double(10, 2) null,
    payment_status int           null,
    created_Date   timestamp     null,
    edited_Date    timestamp     null,
    user_id        int           null,
    status_id      int           null,
    constraint pos_payment_tb_fk1
        foreign key (order_id) references pos_main_order_tb (order_id)
            on update cascade on delete cascade,
    constraint pos_payment_tb_pos_status_types_status_id_fk
        foreign key (status_id) references pos_status_types (status_id)
);

create table if not exists pos_sub_item_category_tb
(
    sub_item_category_id    int auto_increment
        primary key,
    main_item_category_id   int          null,
    main_item_category_name varchar(100) null,
    sub_item_category_name  varchar(100) null,
    image_path              text         null,
    status                  int(1)       null,
    user_id                 int          null,
    visible                 int          null,
    constraint pos_sub_item_category_tb_ibfk_1
        foreign key (main_item_category_id) references pos_main_item_category_tb (main_item_category_id),
    constraint pos_sub_item_category_tb_ibfk_2
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_main_item_tb
(
    item_id               int auto_increment
        primary key,
    item_bar_code         int           null,
    main_item_category_id int           null,
    sub_item_category_id  int           null,
    item_prefix           varchar(100)  null,
    item_code_prefix      varchar(100)  null,
    discount              double(10, 2) null,
    item_name             varchar(100)  null,
    unit_type             varchar(100)  null,
    printer_type          varchar(100)  null,
    cost_price            double(10, 2) null,
    unit_price            double(10, 2) null,
    image_path            text          null,
    grn_status            int(1)        null,
    selling_status        int(1)        null,
    status                int(1)        null,
    user_id               int           null,
    visible               int           null,
    weight                double        null,
    constraint pos_main_item_tb_ibfk_1
        foreign key (main_item_category_id) references pos_main_item_category_tb (main_item_category_id),
    constraint pos_main_item_tb_ibfk_2
        foreign key (sub_item_category_id) references pos_sub_item_category_tb (sub_item_category_id),
    constraint pos_main_item_tb_ibfk_3
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create table if not exists pos_inv_item_store_tamplate_tb
(
    tamplate_id   int auto_increment
        primary key,
    main_item_id  int            null,
    sub_item_id   int            null,
    tamplate_name varchar(100)   null,
    qty           decimal(10, 2) null,
    user_id       int            null,
    visible       int            null,
    constraint pos_inv_item_store_tamplate_tb_ibfk_1
        foreign key (main_item_id) references pos_main_item_tb (item_id)
);

create index main_item_id
    on pos_inv_item_store_tamplate_tb (main_item_id);

create table if not exists pos_inv_purchase_order_details_tb
(
    po_details_id  int auto_increment
        primary key,
    po_id          int            null,
    item_id        int            null,
    item_name      varchar(100)   null,
    qty            decimal(10, 2) null,
    expected_price decimal(10, 2) null,
    last_grn_price decimal(10, 2) null,
    total_price    decimal(10, 2) null,
    user_id        int            null,
    constraint pos_inv_purchase_order_details_tb_ibfk_1
        foreign key (po_id) references pos_inv_purchase_order_tb (po_id),
    constraint pos_inv_purchase_order_details_tb_ibfk_2
        foreign key (item_id) references pos_main_item_tb (item_id),
    constraint pos_inv_purchase_order_details_tb_ibfk_3
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index item_id
    on pos_inv_purchase_order_details_tb (item_id);

create index po_id
    on pos_inv_purchase_order_details_tb (po_id);

create index user_id
    on pos_inv_purchase_order_details_tb (user_id);

create table if not exists pos_inv_stock_tb
(
    stock_id              int auto_increment
        primary key,
    grn_id                int           null,
    main_item_category_id int           null,
    sub_item_category_id  int           null,
    item_id               int           null,
    item_bar_code         int           null,
    stock_category_id     int           null,
    stock_name            varchar(100)  null,
    unit_type_id          int           null,
    cost_price            double(10, 2) null,
    last_grn_price        double(10, 2) null,
    quantity              int(10)       null,
    status                int(1)        null,
    user_id               int           null,
    visible               int           null,
    constraint pos_inv_stock_tb_ibfk_1
        foreign key (grn_id) references pos_inv_grn_tb (grn_id),
    constraint pos_inv_stock_tb_ibfk_2
        foreign key (main_item_category_id) references pos_main_item_category_tb (main_item_category_id),
    constraint pos_inv_stock_tb_ibfk_3
        foreign key (sub_item_category_id) references pos_sub_item_category_tb (sub_item_category_id),
    constraint pos_inv_stock_tb_ibfk_4
        foreign key (item_id) references pos_main_item_tb (item_id),
    constraint pos_inv_stock_tb_ibfk_5
        foreign key (stock_category_id) references pos_inv_stock_category_tb (stock_category_id),
    constraint pos_inv_stock_tb_ibfk_6
        foreign key (unit_type_id) references pos_main_unit_type_tb (unit_type_id),
    constraint pos_inv_stock_tb_ibfk_7
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index grn_id
    on pos_inv_stock_tb (grn_id);

create index item_id
    on pos_inv_stock_tb (item_id);

create index main_item_category_id
    on pos_inv_stock_tb (main_item_category_id);

create index stock_category_id
    on pos_inv_stock_tb (stock_category_id);

create index sub_item_category_id
    on pos_inv_stock_tb (sub_item_category_id);

create index unit_type_id
    on pos_inv_stock_tb (unit_type_id);

create index user_id
    on pos_inv_stock_tb (user_id);

create index main_item_category_id
    on pos_main_item_tb (main_item_category_id);

create index sub_item_category_id
    on pos_main_item_tb (sub_item_category_id);

create index user_id
    on pos_main_item_tb (user_id);

create table if not exists pos_main_order_details_tb
(
    order_detail_id      int auto_increment
        primary key,
    order_id             int           null,
    item_id              int           null,
    item_bar_code        int           null,
    unit_type_id         int           null,
    printer_type_id      int           null,
    quantity             int(10)       null,
    per_item_price       double(10, 2) null,
    total_discount_price double(10, 2) null,
    total_item_price     double(10, 2) null,
    remark               varchar(500)  null,
    status               int(1)        null,
    user_id              int           null,
    constraint pos_main_order_details_tb_ibfk_1
        foreign key (order_id) references pos_main_order_tb (order_id),
    constraint pos_main_order_details_tb_ibfk_2
        foreign key (item_id) references pos_main_item_tb (item_id),
    constraint pos_main_order_details_tb_ibfk_3
        foreign key (user_id) references pos_main_user_tb (user_id)
);

create index item_id
    on pos_main_order_details_tb (item_id);

create index order_id
    on pos_main_order_details_tb (order_id);

create index user_id
    on pos_main_order_details_tb (user_id);

create index main_item_category_id
    on pos_sub_item_category_tb (main_item_category_id);

create index user_id
    on pos_sub_item_category_tb (user_id);



-- Default Data
INSERT INTO petal_pink_pos.pos_emp_employee_management_tb (employee_id, employee_title, employee_name, employee_designation, employee_prefix, employee_code, employee_code_prefix, image_path, phone, gmail, addree, status, user_id, visible) VALUES (1, 'Ms', 'Super Admin', 'ww', 'S', 1, 'S1', '', '0', 'amilsrinath5@gmail.com', 'Panadura', 1, 1, 1);
INSERT INTO petal_pink_pos.pos_emp_employee_management_tb (employee_id, employee_title, employee_name, employee_designation, employee_prefix, employee_code, employee_code_prefix, image_path, phone, gmail, addree, status, user_id, visible) VALUES (2, 'Mr', 'Amil Srinath', 'aa', 'E', 3, 'E3', null, '5144654', 'dsad', 'fdsafdsa', 1, 1, 1);
INSERT INTO petal_pink_pos.pos_emp_employee_management_tb (employee_id, employee_title, employee_name, employee_designation, employee_prefix, employee_code, employee_code_prefix, image_path, phone, gmail, addree, status, user_id, visible) VALUES (3, 'Mr', 'Amil Srinath', 'aa', 'E', 3, 'E3', null, '5144654', 'dsad', 'fdsafdsa', 0, 1, 1);

INSERT INTO petal_pink_pos.pos_status_reg (reg_id, description, create_date, edited_date, status, user_id) VALUES (1, 'Delivery Status', '2025-06-16 11:52:24', '2025-06-16 11:52:24', 1, 2);
INSERT INTO petal_pink_pos.pos_status_reg (reg_id, description, create_date, edited_date, status, user_id) VALUES (2, 'Pay Status', '2025-06-16 11:52:32', '2025-06-16 11:52:32', 1, 2);

INSERT INTO petal_pink_pos.pos_main_user_role_tb (role_id, role, status, user_id, visible) VALUES (1, 'Super Admin', 1, 0, 1);
INSERT INTO petal_pink_pos.pos_main_user_role_tb (role_id, role, status, user_id, visible) VALUES (2, 'Admin', 1, 1, 1);
INSERT INTO petal_pink_pos.pos_main_user_role_tb (role_id, role, status, user_id, visible) VALUES (3, 'Supevisor', 1, 0, 1);
INSERT INTO petal_pink_pos.pos_main_user_role_tb (role_id, role, status, user_id, visible) VALUES (4, 'Order Creator', 1, 0, 1);
INSERT INTO petal_pink_pos.pos_main_user_role_tb (role_id, role, status, user_id, visible) VALUES (5, 'Wrapping Agent', 1, 0, 1);

INSERT INTO petal_pink_pos.pos_main_user_tb (user_id, employee_id, role_id, username, password, status, visible, token) VALUES (1, 1, 2, 'Amil', '$2a$12$S3zHJlvm5vvNKbxO2RkQeugL/f5aTmez1h2xojo8URZx25mqZYX16', 1, 1, '');
INSERT INTO petal_pink_pos.pos_main_user_tb (user_id, employee_id, role_id, username, password, status, visible, token) VALUES (2, 2, 3, 'a', '$2a$10$Tmvuc/pB4o47rz5TkVezUugd3CBdvxSaI8wIZHitxWMIiCoZ6nDva', 1, 1, 'AICZWZHINO');

INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (1, 'Active', 1, 2, '2025-06-16 11:54:09', '2025-06-16 11:54:09', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (2, 'Pending', 1, 2, '2025-06-16 11:54:19', '2025-06-16 11:54:19', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (3, 'Wrapping', 1, 2, '2025-06-16 11:54:09', '2025-06-16 11:54:09', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (4, 'Out of Delivery', 1, 2, '2025-06-16 11:54:19', '2025-06-16 11:54:19', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (5, 'Delivered', 1, 2, '2025-06-16 11:54:09', '2025-06-16 11:54:09', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (6, 'Return', 1, 2, '2025-06-16 11:54:19', '2025-06-16 11:54:19', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (7, 'Cancel', 1, 2, '2025-06-16 11:54:09', '2025-06-16 11:54:09', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (8, 'Paid', 2, 2, '2025-06-16 11:54:19', '2025-06-16 11:54:19', 1);
INSERT INTO petal_pink_pos.pos_status_types (status_id, status_type, reg_id, user_id, create_date, edited_date, status) VALUES (9, 'Not Paid', 2, 2, '2025-06-16 11:54:19', '2025-06-16 11:54:19', 1);

INSERT INTO pos_main_unit_type_tb (unit_type_id, unit_type, status, user_id, visible) VALUES (1, 'No Convertion', 1, 1, 1);
INSERT INTO pos_main_unit_type_tb (unit_type_id, unit_type, status, user_id, visible) VALUES (2, 'ml', 1, 1, 1);
INSERT INTO pos_main_unit_type_tb (unit_type_id, unit_type, status, user_id, visible) VALUES (3, 'g', 1, 1, 1);

INSERT INTO pos_main_printer_type_tb (printer_type_id, printer_type, status, user_id, visible) VALUES (1, 'BILL', 1, 1, 1);
INSERT INTO pos_main_printer_type_tb (printer_type_id, printer_type, status, user_id, visible) VALUES (2, 'KOT', 1, 1, 1);

INSERT INTO pos_main_payment_types_tb (payment_type_id, payment_type, status, user_id, visible) VALUES (1, 'Cash', 1, 1, 1);
INSERT INTO pos_main_payment_types_tb (payment_type_id, payment_type, status, user_id, visible) VALUES (2, 'Card', 1, 1, 1);

INSERT INTO pos_emp_employee_title_tb (title_id, title_name, status, user_id, visible) VALUES (1, 'Mr', 1, 1, 1);
