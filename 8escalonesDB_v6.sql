PGDMP      ,            
    |            8_escalones    17.0    17.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16449    8_escalones    DATABASE     �   CREATE DATABASE "8_escalones" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Latin America.1252';
    DROP DATABASE "8_escalones";
                     postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    16450    admin    TABLE     q   CREATE TABLE public.admin (
    id integer NOT NULL,
    usuario text NOT NULL,
    contrasenia text NOT NULL
);
    DROP TABLE public.admin;
       public         heap r       postgres    false    4            �            1259    16455    admin_id_seq    SEQUENCE     �   ALTER TABLE public.admin ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    217    4            �            1259    16456    participantes    TABLE     ~   CREATE TABLE public.participantes (
    id integer NOT NULL,
    nombre text NOT NULL,
    veces_ganadas integer DEFAULT 0
);
 !   DROP TABLE public.participantes;
       public         heap r       postgres    false    4            �            1259    16515    participantes_id_seq    SEQUENCE     �   ALTER TABLE public.participantes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.participantes_id_seq
    START WITH 9
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000
    CACHE 1
);
            public               postgres    false    219    4            �            1259    16463 	   preguntas    TABLE     �   CREATE TABLE public.preguntas (
    id_pregunta integer NOT NULL,
    pregunta text NOT NULL,
    id_tema integer NOT NULL,
    id_tipopregunta integer
);
    DROP TABLE public.preguntas;
       public         heap r       postgres    false    4            �            1259    16469 
   respuestas    TABLE     �   CREATE TABLE public.respuestas (
    id_respuesta integer NOT NULL,
    respuesta text,
    id_pregunta integer,
    respuesta_correcta boolean
);
    DROP TABLE public.respuestas;
       public         heap r       postgres    false    4            �            1259    16474    tema    TABLE     Z   CREATE TABLE public.tema (
    id_tema integer NOT NULL,
    nombre_tema text NOT NULL
);
    DROP TABLE public.tema;
       public         heap r       postgres    false    4            �            1259    16479    tipo_pregunta    TABLE     i   CREATE TABLE public.tipo_pregunta (
    id_tipo integer NOT NULL,
    tipo_pregunta character varying
);
 !   DROP TABLE public.tipo_pregunta;
       public         heap r       postgres    false    4            �          0    16450    admin 
   TABLE DATA           9   COPY public.admin (id, usuario, contrasenia) FROM stdin;
    public               postgres    false    217   �!       �          0    16456    participantes 
   TABLE DATA           B   COPY public.participantes (id, nombre, veces_ganadas) FROM stdin;
    public               postgres    false    219   $"       �          0    16463 	   preguntas 
   TABLE DATA           T   COPY public.preguntas (id_pregunta, pregunta, id_tema, id_tipopregunta) FROM stdin;
    public               postgres    false    220   �"       �          0    16469 
   respuestas 
   TABLE DATA           ^   COPY public.respuestas (id_respuesta, respuesta, id_pregunta, respuesta_correcta) FROM stdin;
    public               postgres    false    221    *       �          0    16474    tema 
   TABLE DATA           4   COPY public.tema (id_tema, nombre_tema) FROM stdin;
    public               postgres    false    222   �1       �          0    16479    tipo_pregunta 
   TABLE DATA           ?   COPY public.tipo_pregunta (id_tipo, tipo_pregunta) FROM stdin;
    public               postgres    false    223   T2       �           0    0    admin_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.admin_id_seq', 1, true);
          public               postgres    false    218            �           0    0    participantes_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.participantes_id_seq', 9, false);
          public               postgres    false    224            @           2606    16485    tema Tema_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.tema
    ADD CONSTRAINT "Tema_pkey" PRIMARY KEY (id_tema);
 :   ALTER TABLE ONLY public.tema DROP CONSTRAINT "Tema_pkey";
       public                 postgres    false    222            8           2606    16487    admin admin_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.admin DROP CONSTRAINT admin_pkey;
       public                 postgres    false    217            :           2606    16489    participantes participante_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.participantes
    ADD CONSTRAINT participante_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY public.participantes DROP CONSTRAINT participante_pkey;
       public                 postgres    false    219            <           2606    16491    preguntas preguntas_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.preguntas
    ADD CONSTRAINT preguntas_pkey PRIMARY KEY (id_pregunta);
 B   ALTER TABLE ONLY public.preguntas DROP CONSTRAINT preguntas_pkey;
       public                 postgres    false    220            >           2606    16493    respuestas respuestas_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.respuestas
    ADD CONSTRAINT respuestas_pkey PRIMARY KEY (id_respuesta);
 D   ALTER TABLE ONLY public.respuestas DROP CONSTRAINT respuestas_pkey;
       public                 postgres    false    221            B           2606    16495     tipo_pregunta tipo_pregunta_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id_tipo);
 J   ALTER TABLE ONLY public.tipo_pregunta DROP CONSTRAINT tipo_pregunta_pkey;
       public                 postgres    false    223            E           2606    16496 !   respuestas fk_respuestas_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuestas
    ADD CONSTRAINT fk_respuestas_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.preguntas(id_pregunta) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.respuestas DROP CONSTRAINT fk_respuestas_pregunta;
       public               postgres    false    4668    221    220            C           2606    16501    preguntas fk_tema_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.preguntas
    ADD CONSTRAINT fk_tema_pregunta FOREIGN KEY (id_tema) REFERENCES public.tema(id_tema) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 D   ALTER TABLE ONLY public.preguntas DROP CONSTRAINT fk_tema_pregunta;
       public               postgres    false    220    222    4672            D           2606    16506 "   preguntas fk_tipopregunta_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.preguntas
    ADD CONSTRAINT fk_tipopregunta_pregunta FOREIGN KEY (id_tipopregunta) REFERENCES public.tipo_pregunta(id_tipo) NOT VALID;
 L   ALTER TABLE ONLY public.preguntas DROP CONSTRAINT fk_tipopregunta_pregunta;
       public               postgres    false    223    4674    220            �      x�3�L�I��,H�K������� 8c      �   R   x�3�t�I��4�2�t�H�2L8�2�A"���y�@ڌ�=1�(,i��X��YR�����+��s�&�d�qp��qqq ��A      �   j  x�}XMo�6=ۿ��E�b���S�n� IӤA/�p%�E*��M�o|���·\
T�oHj��~\l�#�{�f������A׆��%#�Kf/.���r�V��}���Ђ5���ôa+YIQ��GL�Rq-�]�P��U-
�*��}q1F�4�Ȅ�ۧB�n�W&4����gX~���}�)d���}f��b�蜳_a-go�Ar��b���0P)�A��5��
�����,���Mt���|����X��at-5V���I�d���n5�ɐk�٦�5��+�T��d�r�l��1N�s#K�r����zep�&q`�@�OSH��3Z��|c�箭ēM���K�����d�A���z��a��*��������ǌ灇��J4Vh�zie!Y
�>e�  !��[l7���c�K^����^`ɢ����+���Q�N��m#*��(-�M�u�q�<r(���+w(Y�8`0��2�!a�C�&���DFHz�&��m�/cR�x���,�Jf\�����pEP�[�2k
�K�ZǓs��R����`��OP�s0��,^��#�ބO�į��ǾbP�JdTJ��JDy݌�����d�}��g�u&������F�Xy\�  ��s�R��h��6���	���ɨ(�}Ϟ�DnŽ��$h:^��3�3~φq�7���9Xޅ�<fÓѹ�6�O;oQ	1�\qL�t;<� ņk'��*sW�+�QAbv@�{V��R��٘: L^���̤(eFro��B�>)��a���S��xit�\gr�/ .�#|������.��0tA�t�kE�5��舙�����7��=�:�S"��h��u�+O�49')ܡ�#y�ށ	N{�AU��Wr�'�N.��@���k�$��i�gV7K��u��۬e&�q�(����p�3�iI�/����<]\�m���"�t���Z��J�/ФgH��A���J@�3�i����?;F.1cm�W 8&�?|��-�N5��V4�!�-*�åd�q�Y��
������ry����Kó��}a dh4��~����G�!����AV�]�{d�%y����R��%&$E�:וΕ�p>�-���|E�!���C�7�,�,�ͬ\nW����L�&�_;I[��}�ӹ�P�:!��{9���p�M��D>`(�]x�����|'�d8v�nÓ;S���<@�n�mA�D�T�q�H�zo�����F�	��V��yl�{sc�F�jr���,��iwd2e�@���k�:�@�7#�T+���{>��*@#&M��>��mJ�w`ͻ��mH���Z*X��'��Wi�4x�t
�G
�!�feC��-�֥�����(��������C �O��}��b480����e8G"6�����D�!����r��!v�����j�l`q0 ���5&|P��kx8\]�hmp�N��0�Yq)t&�-a�����#>��n�z-TZ���N��W}����b����9�6i�s&����M/eS�~?�OV(^˭��i�<����e:���&��
.K�R���|Ӥ�w٢�p'��o+n�Κ��1�wW�_)��4:?�0Շ��Mw6��K�����P���Kiw�=�
'��Bv�����t��MXL������-�9?H>���Ei�w��p�ʭ�����W?�
嶤�K���̱��j�����rԝ��a�-�yƪ��E�"�m�]��v�L��>`q��Y����X�BĐ�p5ş�{�a�%�a��iC}i7�
0����n��Ԅ̯%Q>�+.�v�A/�4E�f�&`���\��@w�p�X	���O�d��9tC�5����O��ġ~��҇$��i��ո��Q=�������q=F      �   �  x�uW�r�]��b~ *�XJ��d_�V,ũTe�0�h8P03�����|��Zx�������n��(�*�4��4s*j�i|QP��T`T��ߊ�ł*��KM5������_��
_�j|��9��0�F$��!/1%�9�����������9J�0�ʁrE��`�Ƭ
S9b���ׂc9�
9S��
Y�T4Tp�Bo�����ao�Cl�U�eJV�����%B��8`�CR��iY�Q���X%,+��p%_q����Wr���QɡJ��.9�[#��SV5U��y'G�
8W�*y$oVa/G��8R�`WG��*T)>~ő*�oTq��#�o��1��g�����#~��>A*ٶ���n4J5��g�0pP<Q)�ƚ�ڰ���T, ����� ];�IrAoMmK���7a����>�~�Pa��f���ҭ�0xV����cvg���o>'KC����6{�{;&mK�_�բPtbC7�B��5Q�1/M���;�?��Ό07b.���YfsI�ݴu��ɣ��a/�|\糏&l̐]t~�R��:3��l����������-�s�������f�0�ԇq�voB���ݼ�aZ���~5?��g�c7�G��Fۀ�d�4��)�8��i�w�~4�8��+�k���RD�$Wf�By�������sf�]}67v��&XF3���k��~�W{7ܸd��f�Om F<m#�G�]ػ��w�8�HS�r��{j$�u���R�a\�;s�%��?!�H���<���J�l4�P#�F�|
�-+�@��j
�v�P��v~H�)qiݝ�S���]i}����~&��IDmK�fi���H�[N�����������������U�����%8��t���c��J��a�Hɳ+z5�v|2��׃1+ �_����v�-m�W��$'vo�2���v_��޹ߙ���IZM��#�	l�]�'"�p?��� ��_�rI���F �2ķH���5��do能]�����<'��V�j�a��t��g��qA�qs?��]��z���HU�Y����3J�-�~up��5��
0���@W5;Nh�
@>��$)Z5(�Qd��`D�.��iX�h�I�֠JR�G �Z����qs��>��WB1t�n]�i�o�ߌ���4-��7(���@Q��Q���٭�]t)���vrN&;qKQIc 2{���ϓ��G�j�+��l;�I�q�]v��n��5wL���[�.pM�2�ǡ��-��@�x
�V��/ȍ�L�_20O��I�r_W[-����?�MXË��0�.M,�Ql9	��!�K��!��­�qUQ�t���"r/���`�{g%~t���T.��;M��{�:^�:�~O~u�^�ؤ����_m�-�"�UJ�(kzip��pe>@GI��{dD�\:�`X��x4+�5�X�b��{ ��(y�kV����_��QÁ�n|���K���UW&,���cv��Yf�;�$`!��t[\���:�r���	!�l�8I�`�7.�O&��>}���*ET�){��
��Ǵ1]�%-� FP^1��5wJ=�xV��]rR��~=�L��x����)�W^��,rS���?�%����`�7<ҔO(Q	�����J*I���2Nj��nԷ(���Ti�Xӕ�}���j�%���n�I-9��9�S -�O~0kp�ȃ"��[f����5�Q�S�i93�4�AIK^�⮦��m�gD���Q�Xj/��,��(�9X�	�wm��"� �� J҂[��	�ٱ�&[�Q��~��έ�޳���R�l#��(x���Z��R����t���n5�M�'��N���A�a^�j�5��/�� �睖4@b�0!Br�W�U�/�z���ݗ��b	��N��4.�V0j�]/����<r����q��@ך�������kI�"u�ѧ���[Y��X�	 ��8�^�,�;�ߏ^�x�_��5�      �   d   x�3��M,I�=��$39��ˈ�935/93�˘�#��$��4�tO�O/JL;�6�˔ӱ�$�ˌ�'�$�(���(�˜�%� (\�e��{xW1�,�=... �%      �   *   x�3�,(JM/�+It,(ʯ��ML����2���b���� x�P     