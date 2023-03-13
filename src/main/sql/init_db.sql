DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;

CREATE TABLE public.product (
  id serial NOT NULL PRIMARY KEY,
  name text NOT NULL ,
  defaultPrice decimal NOT NULL,
  currencyString text NOT NULL,
  description text NOT NULL,
  product_category_id integer NOT NULL,
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

INSERT INTO public.product_category (name, department, description) VALUES ('lego', 'toys', 'A line of plastic construction toys');
INSERT INTO public.supplier (name, description) VALUES ('LegoWorld', 'Lego Supplier');
INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('LEGO Star Wars - Dark Trooper Attack', 68.99, 'USD',
        'set for Luke Skywalker vs. Dark Troopers battles – Fans can relive Luke Skywalker’s return.',
        1, 1);


ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES public.product_category(id);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);