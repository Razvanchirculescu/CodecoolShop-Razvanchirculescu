DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;

CREATE TABLE public.product (
  id serial NOT NULL PRIMARY KEY,
  name text NOT NULL ,
  defaultPrice text NOT NULL,
  currencyString text NOT NULL,
  description text NOT NULL,
  productCategory_id integer NOT NULL,
  supplier_id integer
);

CREATE TABLE public.product_category (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    department text NOT NULL,
    description text NOT NULL
);

CREATE TABLE public.supplier (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text NOT NULL
);


ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_productCategory_id FOREIGN KEY (supplier_id) REFERENCES public.product_category(id);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);