DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;
DROP TABLE IF EXISTS public.order;
DROP TABLE IF EXISTS public.user;
DROP TABLE IF EXISTS public.cart;

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

CREATE TABLE public.cart (
     id serial NOT NULL PRIMARY KEY,
     user_id integer,
     product_list text[] not null
);

CREATE TABLE public.user (
      id serial NOT NULL PRIMARY KEY,
      name text not null ,
      password text
);

INSERT INTO public.user (id, name) VALUES (0, 'Guest');
INSERT INTO  public.cart (user_id, product_list) VALUES (0, ARRAY ['1','2']);
INSERT INTO public.product_category (name, department, description) VALUES ('lego', 'toys', 'A line of plastic construction toys');
INSERT INTO public.product_category (name, department, description) VALUES ('Doll', 'Toys', 'Toy for girls');
INSERT INTO public.product_category (name, department, description) VALUES ('Car', 'Toy', 'Toys for boy');
INSERT INTO public.supplier (name, description) VALUES ('LegoWorld', 'Lego Supplier');
INSERT INTO public.supplier (name, description) VALUES ('Duplo', 'Lego Supplier');
INSERT INTO public.supplier (name, description) VALUES ('Barbie', 'Barbie Supplier');
INSERT INTO public.supplier (name, description) VALUES ('MOONTOY', 'Cars');

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('LEGO Star Wars - Dark Trooper Attack', 68.99, 'USD',
        'set for Luke Skywalker vs. Dark Troopers battles – Fans can relive Luke Skywalker’s return.', 1, 1);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('LEGO Harry Potter Hogwarts Magical Trunk', 51.99, 'USD',
        'Personalized and portable, the LEGO Harry Potter Hogwarts Magical Trunk for ages 8+.', 1, 1);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('LEGO DUPLO My First Alphabet Truck', 42.40, 'USD',
        'With the LEGO DUPLO Alphabet Truck, preschoolers can combine creative building.', 1, 2);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('Disney Princess Magic Travel Toy', 35.01 , 'USD',
        'With Cinderella, Jasmine, Rapunzel Mini Dolls, Toy Horse & Carriage, Flying Rug, Hot Air Balloon for Girls and Boys.',
        1, 3);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('Barbie Travel Doll', 30.00, 'USD',
        'Send curious minds around the world with Barbie doll and a travel-themed set inspired by Barbie Dream-house Adventures ' ||
        'that comes with a puppy for a travel companion!', 2, 3);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('LEGO Friends Aliya`s Room', 18.00, 'USD',
        'Children aged 6 and up can host a fun sleepover party in LEGO Friends Aliya`s rooms. This fun minidoll play set stimulates ' ||
        'imagination and inspires creative stories with the figure.', 1, 1);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('Barbie GBK12 Portable Wardrobe', 29.08, 'USD',
        'Barbie`s dream wardrobe testifies style inside and outside with the included Barbie doll, fashion
        and accessories. The pink wardrobe has two clear cabinet doors.', 2, 3);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('20 Metal Pull Back Mini Toy Cars', 29.99, 'USD',
        'SUPER PACK. Our pull-back model model toy cars from model building includes a total of 20 toy cars:
        police cars, fire engines, bulldozers, dump trucks, mountain cars and other.', 3, 4);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('10 Scale Brushless RC Cars', 247.62, 'USD',
        'Whether you`re steering on sand, concrete, or anything in between, these RC monster trucks for boys and
        girls are built to withstand rugged surfaces with expert grip, operation, and durability.', 3, 4);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('Puzzle Car Tracks Playsets City Rescue', 19.99, 'USD',
        'Fun manual operations toys set. Parent-child educational toys. Imaginative and creative design. Safe and
        environmentally car toys set. Perfect gift for kids.', 3, 4);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('Cars Set - Bull Truck, Leopard Truck & Crocodile Trucks', 19.99, 'USD',
        'MOONTOY monster trucks set include 3 unique wild animals toy car figures model: bull, leopard, and crocodile.
         No battery needed energy-saving and environmentally friendly.', 3, 4);

INSERT INTO public.product (name, defaultprice, currencystring, description, product_category_id, supplier_id)
VALUES ('Paw Patrol, Chase’s Movie Transforming Toy Car', 10.89, 'USD',
        'TRANSFORMING TOY CAR: Push the spoiler to armor up Chase’s Deluxe Vehicle, revealing a projectile launcher in
        the back! All of the PAW Patrol toy cars have their own transformation!', 3, 4);


ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES public.product_category(id);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);