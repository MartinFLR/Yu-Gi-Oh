PGDMP      -            
    |            8_escalones    17.0    17.0 %    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16970    8_escalones    DATABASE     �   CREATE DATABASE "8_escalones" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Latin America.1252';
    DROP DATABASE "8_escalones";
                     postgres    false            �            1259    16971    admin    TABLE     q   CREATE TABLE public.admin (
    id integer NOT NULL,
    usuario text NOT NULL,
    contrasenia text NOT NULL
);
    DROP TABLE public.admin;
       public         heap r       postgres    false            �            1259    16976    admin_id_seq    SEQUENCE     �   ALTER TABLE public.admin ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000
    CACHE 1
);
            public               postgres    false    217            �            1259    16977    participantes    TABLE     t   CREATE TABLE public.participantes (
    id integer NOT NULL,
    nombre text NOT NULL,
    veces_ganadas integer
);
 !   DROP TABLE public.participantes;
       public         heap r       postgres    false            �            1259    16982    participantes_id_seq    SEQUENCE     �   ALTER TABLE public.participantes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.participantes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000
    CACHE 1
);
            public               postgres    false    219            �            1259    16983 	   preguntas    TABLE     �   CREATE TABLE public.preguntas (
    id_pregunta integer NOT NULL,
    pregunta text NOT NULL,
    id_tema integer NOT NULL,
    id_tipopregunta integer
);
    DROP TABLE public.preguntas;
       public         heap r       postgres    false            �            1259    16988    preguntas_id_pregunta_seq    SEQUENCE     �   ALTER TABLE public.preguntas ALTER COLUMN id_pregunta ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.preguntas_id_pregunta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000
    CACHE 1
);
            public               postgres    false    221            �            1259    16989 
   respuestas    TABLE     �   CREATE TABLE public.respuestas (
    id_respuesta integer NOT NULL,
    respuesta text,
    id_pregunta integer,
    respuesta_correcta boolean
);
    DROP TABLE public.respuestas;
       public         heap r       postgres    false            �            1259    16994    respuestas_id_respuesta_seq    SEQUENCE     �   ALTER TABLE public.respuestas ALTER COLUMN id_respuesta ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.respuestas_id_respuesta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000
    CACHE 1
);
            public               postgres    false    223            �            1259    16995    tema    TABLE     Z   CREATE TABLE public.tema (
    id_tema integer NOT NULL,
    nombre_tema text NOT NULL
);
    DROP TABLE public.tema;
       public         heap r       postgres    false            �            1259    17000    tema_id_tema_seq    SEQUENCE     �   ALTER TABLE public.tema ALTER COLUMN id_tema ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tema_id_tema_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000
    CACHE 1
);
            public               postgres    false    225            �            1259    17001    tipo_pregunta    TABLE     i   CREATE TABLE public.tipo_pregunta (
    id_tipo integer NOT NULL,
    tipo_pregunta character varying
);
 !   DROP TABLE public.tipo_pregunta;
       public         heap r       postgres    false            �            1259    17006    tipo_pregunta_id_tipo_seq    SEQUENCE     �   ALTER TABLE public.tipo_pregunta ALTER COLUMN id_tipo ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tipo_pregunta_id_tipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 11111
    CACHE 1
);
            public               postgres    false    227            �          0    16971    admin 
   TABLE DATA           9   COPY public.admin (id, usuario, contrasenia) FROM stdin;
    public               postgres    false    217   �)       �          0    16977    participantes 
   TABLE DATA           B   COPY public.participantes (id, nombre, veces_ganadas) FROM stdin;
    public               postgres    false    219   �)       �          0    16983 	   preguntas 
   TABLE DATA           T   COPY public.preguntas (id_pregunta, pregunta, id_tema, id_tipopregunta) FROM stdin;
    public               postgres    false    221   �*       �          0    16989 
   respuestas 
   TABLE DATA           ^   COPY public.respuestas (id_respuesta, respuesta, id_pregunta, respuesta_correcta) FROM stdin;
    public               postgres    false    223    \       �          0    16995    tema 
   TABLE DATA           4   COPY public.tema (id_tema, nombre_tema) FROM stdin;
    public               postgres    false    225   z�       �          0    17001    tipo_pregunta 
   TABLE DATA           ?   COPY public.tipo_pregunta (id_tipo, tipo_pregunta) FROM stdin;
    public               postgres    false    227   �       �           0    0    admin_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.admin_id_seq', 1, true);
          public               postgres    false    218            �           0    0    participantes_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.participantes_id_seq', 21, true);
          public               postgres    false    220            �           0    0    preguntas_id_pregunta_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.preguntas_id_pregunta_seq', 520, true);
          public               postgres    false    222            �           0    0    respuestas_id_respuesta_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.respuestas_id_respuesta_seq', 2045, true);
          public               postgres    false    224            �           0    0    tema_id_tema_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.tema_id_tema_seq', 9, true);
          public               postgres    false    226            �           0    0    tipo_pregunta_id_tipo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.tipo_pregunta_id_tipo_seq', 2, true);
          public               postgres    false    228            ;           2606    17008    admin admin_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.admin DROP CONSTRAINT admin_pkey;
       public                 postgres    false    217            =           2606    17010     participantes participantes_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.participantes
    ADD CONSTRAINT participantes_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.participantes DROP CONSTRAINT participantes_pkey;
       public                 postgres    false    219            ?           2606    17012    preguntas preguntas_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.preguntas
    ADD CONSTRAINT preguntas_pkey PRIMARY KEY (id_pregunta);
 B   ALTER TABLE ONLY public.preguntas DROP CONSTRAINT preguntas_pkey;
       public                 postgres    false    221            A           2606    17014    respuestas respuestas_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.respuestas
    ADD CONSTRAINT respuestas_pkey PRIMARY KEY (id_respuesta);
 D   ALTER TABLE ONLY public.respuestas DROP CONSTRAINT respuestas_pkey;
       public                 postgres    false    223            C           2606    17016    tema tema_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_pkey PRIMARY KEY (id_tema);
 8   ALTER TABLE ONLY public.tema DROP CONSTRAINT tema_pkey;
       public                 postgres    false    225            E           2606    17018     tipo_pregunta tipo_pregunta_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id_tipo);
 J   ALTER TABLE ONLY public.tipo_pregunta DROP CONSTRAINT tipo_pregunta_pkey;
       public                 postgres    false    227            H           2606    17019 !   respuestas fk_respuestas_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuestas
    ADD CONSTRAINT fk_respuestas_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.preguntas(id_pregunta) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.respuestas DROP CONSTRAINT fk_respuestas_pregunta;
       public               postgres    false    221    223    4671            F           2606    17024    preguntas fk_tema_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.preguntas
    ADD CONSTRAINT fk_tema_pregunta FOREIGN KEY (id_tema) REFERENCES public.tema(id_tema) ON UPDATE CASCADE ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.preguntas DROP CONSTRAINT fk_tema_pregunta;
       public               postgres    false    4675    225    221            G           2606    17029 "   preguntas fk_tipopregunta_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.preguntas
    ADD CONSTRAINT fk_tipopregunta_pregunta FOREIGN KEY (id_tipopregunta) REFERENCES public.tipo_pregunta(id_tipo) NOT VALID;
 L   ALTER TABLE ONLY public.preguntas DROP CONSTRAINT fk_tipopregunta_pregunta;
       public               postgres    false    4677    221    227            �      x�3�LL��̃�\1z\\\ 8Z      �   �   x�%�Mn�@F��)�U�?�
HՊHU�7`�`Ka��t�"�a.VCw����޴ϗP�q{ ��]�Z��C�D�A�|�§�98C��Vw��!�0GZ��ϩ/����=�����*��i���2�&����|1����l�(a�ds���'x��JҰ���-��`j�S��f*	�k�Puz}�����SG�      �      x��}K�G��Y�+��j++�^�F�HJT���Խ�^<#<3���G�Y�����t� <,x�e��2�d�3s��xdI��^� �_�����bq�����w�υMj��*T��d[�T�6�t��bi��ԺU�Jvjk��|u���O�+�/��cR�RW*O6*��/-M��Z��JӼ*��rCn&LM�c��W��^�����hZd}��������om������1oiM�ʚ�ۨ�~8|Z���$��ܸ���U+˪�*�5lki�M�w�v�/����Mc�ZΤ+��l�U�JR[��IUE+���ou��n���6?|L��<�;��re�B���L� ��M�F]������l���R'�\���_�Mα��>�f!:�2MFw��:��{�пq�B�nK�3L�~�"�@[b�����
��h9>��Pr+�Ja�;�צ�ݝ��2� �b���k9F�����us��&[��6�O���L��ҥ�����-��*k�ƺ�x 褉P��{l�6������ܘsK�F����t�X��/cz��� ��<�gW��r�T�/:�{c�3yB������QAU���X&�Z��{R4�&l�"b&�����5�G�QPyv4hoK�:�S�:Z��[c'���-�y��;��tnS��,�u�'����N��O6����?�����XB�W���۽�qDJt��-Ԉ�ܯ/&�ɝ�~Zh�N_�&�#.��C���)���Iƹ�qDF�ib�	W��z�UE&�u�~�]�nz���,un�?��u[�U�ֻ>��'o�@�W�j\�6���+\]���;y�-��xc��i�Ie8�_������o�d�j~$��t��0
�݀���V��R�%�����������ʃ���fM��Ĺ.h�4��y+��E��uS���7�a�ç��I�z�,�DU`��Ms�*��~�w)�ܜu�ܚ��u�h�$��������������,i_�ɳ<y]>�������!�Iᬅ�[,I��szن��F�b��TU��s�Q�SU�jJ7�:he�39�VHEG�jqկT�i��&��A�n�z�G����V�*xS���*�����j�,�|v6���Y(B��zM��zNC�����U1tB�dm%#�h.<)z�-~��.���h$�H�������ݑ���-+�d��cz��=�X�ˀ�Ǩ��s2������QV�*��޹�$2��U�k�o����؈Ih�����Ot�3�����@���Ne�X������4e����A��o������2x�)�]X^�,�L�nX��T�,\�l~�d��a��R���x��}>��K������8����7m��وyh�!�32���1o�$�Ʃ��:��H��@�D�i|D��,2�
Րz�V����_��&>T��~6�)U�F��xf�I�G]����2P�=i�/�45s���<�\�����o5�eEBD�����]ik�y��{#3?p�jy>�i�$�i���d
k���?��I&Y��}�̬��,�)����Rl�x?�3��J�zT���g6-����ȓ�<�E���k�z�Լ:�Z�lR�7��I��{�6pK�Z�-u�;|dۘ/~�$ޚ��ӹ=�Eg
ѓ�u��#��")�~�H���N�qWd1��|G���f-����dU�ti���RN��Ag����Ӈ����)��6�$���#��ǘ��7Eg&���r[t�=�*Y�+������`)'���#K`%f��^E�O��Q	۝����RK������X����8�x��4�2-I.v��ݩG�6���MN/��D��A9��;S{!^�L�ز�C?)�N��W����~b�>��ȇ���lZ��@8h�W&5}?��z����V3o�Z�5J3���-��Nϙ̆������F^�O8AU���̮�o�rW��<i�ײ�G�^-ĒlF$���Ր�V�	�N��e���%˙�7L�C�����l��Sj�������j�ma~>�m��+���q���vё���ធmB�	8����]�JBN�g���`_S4�\��D���iL|I���[�p�o+���ǋ�n��.�a�mN2��R&�.�
1nv��ܼU�7=H�-���vk�)?@̗�w=��=���.�6 B):.~�s��3��CCJt���%B]��ܑ�����lC20�w�
@ƼmI������ݯ:>��n������>b/'�n�M�1.F�	�2r���8�	'����G������xe��H�q)���������Eڪ���NV������{��c��s����i���ُ�d��j���~YQ����*��bj�����C������+�2��S��9^&z�ۡ2��b��mƲ-�D��tDg�>FU���S�`�\/��w�K�!np?�<��'õm�H��P��m����ֶ/�_���n�YU�<��F^@\\Iۡ�&��DxЈ�g� '�V�;�<U�_�ѱ�!b���+�U^~{u,l�dv�$55�ď�'�ug�D;4%kOz&K��k�~�_�j68YJ�7%EO���3��.�C~k�?��H>���l[b���>���60ke��	^jb�
�ZV3~DD�!�
�ih�0����6�9� Ap���d�HF=G�20�\H�x�O�2X~�Q��M!����3;��i�reK�Ș�Q��%��+]~������Y���A¢��'4dT�;��?��/�����h�l�}?`~�u���ׁQ=���`�bU�8q̧��,Ǝ���Bcv�l
<3�)?x±�/r��U���hL�s׍��tK�Z����S���L�)anz�/�w{��֏�� ���bH7[�Y
������	��҈v��EX�E"?1�c� �𣋾��m�٘;Ɍ�0k|�ot�h���]����h�~�5u�����9�A�ڽ���ltǎ"A���B>�291���䄞�SZ����3���%��� c�:��b/���S��RHN)(Br� m&���fe0OZi�)�i��ܦ��l^�	y�l�v�yubؓ�uK9B�+}ߒ�~����0PY�L~�gA��׽ۜ]���� �/�G�C$�o/�/�F��	g�ǔ'�����q[N������Op~6"_<O�,��y>2"d����#V5��Q�u�EvC�cΏI��ɀ�V<�iO��tu���j��$o��Iq��H�F�
��D��.�M�wC4��8�BYI�+5&`�"~&���'S#"��+m���=�ٟ~����g���2��ؐ���mn+I�f����Aex�l��b]}Q��"�����U��t���v�$r�#`H����麁�岘[Eg��	�Z�����`��P ����α���;����b���;U�+�4����(��"'N204��,yN6�di�y�P����>K� ��d�)��#[�OZb���:m��o����{>8��{��`(�Ĥ'�XL�>����
6$`%JK����}�5�Ry��d��Xy}���j�IS����:�Od���[�V-+�@U),�W�������n��M�υ?�u���D"ým|��*�M���Q��,C�얔[���AP����̶���C�Ѥ�uY�Q��r��͗�jH�m��l�~���L�WHʓ����'���Z'?V����<tb��ɻ_��o>��qD�٤xDߵk/�棴�=�I�kN�|��ci<��8��w��HgچL���+�m�@�cٝ��$oS�<7��h��9n� ��@���r?^L��x�sX�����{O�E�xCW�㑄���l.�����nA�z������+��Ţ�i����ζ,�8[jX:��<�Cz!�Z7�Gr��*�ȭ �1]��`��i8�-���y*��wE���]�2Mj�a^�K�<Ǫ]/�~���q
��Ē�(��Y�ᓉ\��KRn]�N�9'�}|���瞖##�V�`H��*<�c|$hC��ɝTd���2�S�"�����������i���_��|��v������YZdZ�����i&4y@M���t����a��-h�'�    ��a��=y��|W��$'~�*�����>��{bU�__N=�pz�-�,��k�Φ9:��:Nv��@zٗ�/]A>q�I�8�wј�'Ƿ-�k�r�1%��R�,�c�b�y#�X#f*�z�$�����ɇ��i!��³�3��l��[3&�;��4�?!#hJt�XJ//�'�|����A���:���L��v�l!Y"a=����|[{�o|�����O��g�~�ɠG�ygɤ�͛U0�x�U۶�u���I�zB>��YAȽ�n�y�=�H�P���η#�g�YΏ�W���ޅm���,���|v���@���	"�',�XxK�A3�p�q*�H0;����OڷkJ$:��s,�;����Y;���*"Ý&�{LG��)
�m#X4N����8�{t���X2O����+?�g�
����}�L+�3`d��?={~;��0k������2�z�y}v��N�)�.���X�I���,xN, �(��� -ɷV�]��ؽ �������WD�5R<�n�����A�eQ	;l����'�'!S̊�L"C`(b�� e��䠋��b˶HNn��z�$�I���[���D7�a�vE,S��!uM���<FH� Os�#��c�&f�K]Bɮ[ �+��W��oEz�K�a���Z��Gu�)���eF���m��&�͞D)�q ~Z2���=����Hw����
���"�#p��E}���(d���t�däz #0BL]��(A����L�~Mf�8�|�\>d$Oѓ��vgj��Z晏�>[�f��5g�zk�Ә���>���� �#2G�K��b���
y�y�ݦ��	C�q���Į~�^p��iK��@�`���'A&E���4)z��`� �\f]�n��^w(��g2�x����ik����g�0���ǈO��
l�<�����ζ �
@�D���oڂDk������6��[+CH��4b�8ɂ���\��j���r�A�b��&+�}l��%!��a��)���=�O�%e�ǚI$ԑ�nc�&Pg񻤤��R�~@/z���xa�CN��H�����3�k���ݨ�L��%�୒�񃮦ܢPɽ�w��jrV�ʷ�N��)Zi'"�otӤ���~���I�Jm
��y�̄�tX� ������i���iM�͉�c�(��1(���yH̦*Dt�v�3�HW�#�"�ܟ��/��B!��|
nAږ��ҵ�,�mΊ@p��("t��-��4�m�Z?j ��S�T���mNK��1�D
��?��đ�ܖf�!���*S
�܁ǐ��ӎԚ�A26����"��LbӶb>%I0;����y��~!�4'ኄ���N?��Gս��U�	o�/&�չ�:�Ho`����Fӟ��,N�|Y��ރ�a�"�GEE��E�S�9��J����	�w�
A?0���rh�e�.)��H
�y�����/^�yX���*��/(�\Q"㨰G�I}� �q���8`"�s<�+#: �r���I��X�(̮��~pg�u̟i���u1\ �ϺnQ���<�$���?Y0�\�4���`���)�j��c�����8 ����L\��	�C
��X�6����*�q;k��>L�� �a�
�L^E���L�2	=��~�Us���w�q�P�6QJB����)S2�����e�J/ɝͯ��`r���a'��B�漯��D��/[�2b�.���_�w�aݰ!��=�?+��:YkK/��8�Gj��F��G5�9��/�U�S?Q��S.���-�C��Z?���I��m>ㆃ=!9�Fҷ-y���$j����+-q��0h�aPvCt�c�k�0@^�0�V䌓A7�`�s��L�]����9[�a�2y�M�����SAt��D�bI�W�] ��5<#�_ nsn��u%BlE�F��B C�Eu
`gWS�e��S^�*���W��2B����ͲS{� �v -.�cݾ2)Kw�i�=�U�/aeԡdG������<�a	���� "2�`#K����K��8�7-�0��\��گ*�Kz��2=W'�A��^Ń�@/��k8�Y8D�1/�Vt٧�F���Ѫ�l��I�培�|�V_ʮ�f�U9y_�5�Ӛ�5[ۣB\��j����J��7L�]Y�d�GF���W����T�s'5��L<|�V ����<Lo��	&�������Y7�λ+�	@oC�$C�ɫ����T"�J�N;WD+�~ʨ:��6"?�9B
�0�P{W�~�̩4��\�V�N4��d�V���]Ѱ �ty�%;z��K�=	v�4@kC�ȕ���8�v�;�CJ��j-�N�x��f
̻C���_9_���\����y�b�5);ip�*=�������iD���̺��פ7�놈�	oޏ	|�<Vg=pR��y���YT&�V"��C
x0گ0Y/R9��S�u���(�<"MY	 8��韸W�~�Qvt�<G]X���pW���z���?�M���:�^\jVe�J}���/�Ʀd�1)��]��b��h���n�=���m6��q)��٥�!7�D	A�.ȉ� U��0erK_����L�yx2��l��+� 9!�K@���!���-��,���h��IZ��%�c,Ţ�h��[X��p�c<�"�?�o��^\������QT��y������]�Hf��:;�$uW�����6c�]Q�>�XV'I�ޒ����W ���"#�̊Z����أ��f��Wa���U�K>�Y�^���w
���&�-<�$���|J��Rl�A�o�W�H�0Ͱ�<��m���(떸<]�(���f�V�%9���}�;�hS�_\�;�XWZ���J�]!�V�M�6���_���"�L�M�I%�����:���gIA����@���Ay3��M�J��PN.M�:�[�s��t�k�o��F,�K�5f��$� $J2���@�7I�l����2+$X��� �pͱ����Qan)��эq�[]Ma���Ha�*N�͒�q2\�)�<�b� ؋�����n��e��_��y9#��F4O !GlwJ,2��{�-���N�K����f
rL3�m���i�ү�6L!�<ȡ��2�d�}�#��85�`<B'Վ�stN��tx���]0�gQ�Dz��k�w����TS{N�AR�j0[8�		4g��2�pkЩF{)������̩@g!{x{�H�[+�^P�@�U�j;�&���ƣz��fnƳ�U���u*R(�p`�q�=�|�W�� {b
�X��{�:�MS8x~���Ռ���~��5�y�R��w�|�;&� �޷�	�X�� ��H�2��4���;a/�|%qÖ�q*��9J��@����/�X�9����@���tԇ]F$1))����v�(�CʔV�)��p��.����l1���\\��	�b&���im9a�x�(	Oi�q��
9�~��5�fP@�3P� ��]n��4�{�;��=��o�����[�h#�_��~l�E����.`.�ۉD	l�~ަ�pWp�2w����n�q��'�9�����ʝ��R��5l�Z�l�<"���� ˲�ͣ��3�]�F�B��{���������3��r/�u��R]}7��a1�-�j��_���˸�Ze���ʿ��d{Nl �	y���[C��CX$ ��Va�G~�r�W�76;v)���A�:�~��3��#��3���P�D����n\��`uwܺ<|�i�������6�"���\ތ,�ԋ!���ưe��<�G�n�Z�3��Ԇ��&�g9 Fd'^�M�����ݝ��y0n�5M%��ֵB���6��(x�C�J��� '��|���,Rd�Ŕp7�!/t�e�n��Xd�Q98Q��&�ՉBf�{'~p�)D��9I]k����S�a���Q���_�g��?���9}�@w��d�$wİya���<$
����Mq}6E>]���䅒lT[E����pi��W��B<WN��>F����ƃ��hKpzj    r�#��֏�������f�b���7����Ė��T�_����ʠ����k��V,I�ғ����2r�G�p5�P%�s�ʸLX�H��Hj���%���e�^m�]6��+ؐbh+n 4:����|������hJY�h���4��:E6n8�fЋ#7����>;��0j��$��hrE{���tӺ�͹S6;�2��x�G�1��g�R6S����D�Z�Tz�J�Bй�c1��C�pSU.��AP�j$�}1��N�F{X��2��vTlԃ��_���أ=�T5ڏ�b���p��ZZM�Z7�Oc^�!���{qv������8%(?Ϭ/�O������,�-`4{�����������''a:�� �&���8��&���D"@h%��o[Nk�G�f������2��\��Oq18|��T�G�*��2��Uz����cl�^x���@����Uf�C��;e�k;!��߷z<�����3/N��ۇMGM����[�6�G1B�q�O��e�jFL���g�n##˝�Տ[_�qx�b0�	�����&�u`1��� �n2]J�wy�'l��	���Y?�ő� 믏ۑ'�֠�Lrw�X�*l6��c�m�[�nL�����e�?�=�l4�/]��"����v�r�7����[��!�����P�F>���������o�����|��m�C�I����5ھ���	-�Tι�ʺ� �qLGRoӽ����Uhw�LS�@/=�.���!q���*FͺW��!\�K���n���~�:s����%ó�:J��P��B��h�Fϲ��2� �M�	�ЕY3���E�5 ,��\H���}.��}l}'�*���� g�6`���M�ؼ�>�1@B@���Bu�l
YAߋ"�k!P�Rڜ�a�?C����c�|��q�8�b	r���p��`�:�#��G���f���J":��Gmm�8�"F��9gW�q"�9]aEV�;%����!~/�����x�&���QoU҆����O�AS>��-~�q�,����h݂���`���G�~����\/zA�#�M��k�C7/��K��q�)�.���z�.�Nza@�W�i�=�zz}�N��FÁ�n9�_�ƮuJD���֭C��W����4~������,^�D\�ں�Q���-�N^��϶��~c#`�X.&J��4�))&z4q�����X4�b��r9#����7���p6:�y�[r)�QB�|���tcj\˗�v�0�����3�0��T��ǩ�y��%��5��w�z�^W�wR�2H�n[������d�i�]��%���2���r7#�A>�Rʿ�`u1L��$Lz�U�m�58+*Zc���EhWʋtHk��?MFIh�`xm�� p 5G��{x 1�Hd���z����((��·� y4�A?wET~��O�֦�$ծ����h�mD�U_Y[>�n�5|�Ż��k������R Ƙ�W|�5��[Ef�����K�6���uZW�PY�BIq���v?}��L��lu���U�Z�8�e�����Y��d���4N{o[8�q���wS����;kR�|D� ���O���TQ�l�K��-��?V�&e�V3���_��(�r2�VФ��lԶ����_�ރ��]�T!�/�=�0h�����M�� ��@:�e�Y�o+�^G�j��0nX�"�
dU���О�����*x����\�Rf���B���u�x��ݡ�?�.i���^��-W9�M	�{�[�s��A�v3�!6��,.N����{W�@�7ZZP�����7��Fea٨�'.˂�nG��Kp1O]�챡���?���T���~Xz�pok�B�Q��'T0��'�Q���D��&�k�|�/.�g����Ӗ���q���C��Aʽ�:���z`����4��K�����q�����Į�Q���@Ŝ�v�{��G���{vK�%�R;o�QFoa섺&��E<jk��}�pI^���'K�:�m�hfxg�]�]թhngd�0�Q�>���n��H�y�X�]�������"���ŋ��j�$[��k���s=9H��1<!<�4�A�oQ·w]�e��qPj"�d{M"z-g�����pY��2��bWG��_삇��-9_�J����V����������c�G?������?�g�}&�l{�n�·;ZZ�Qx�Q���,L(?�!>p�+|���:��pk����D�ւ<uR��"��l�w��;��3ND Y�h!��A�~ic3>�D��n������-�����4M%�_+�ȗ��� "2�+y��^�S��,�{g�|}�\҃cU���$#�EoMF�MYe��TI�׮�C���m �Nr�,�D�M��;|���ϯ���Ԋ����NV��N��L=�0��׿8j%aya����4N�ry����J7g�W@ĝ�l���-�2�p(UN�}P���w�b`�����L�%$���W}N`���d��v���Ȃ@!g���Ҩ�O��j3z �$ �`���g_��]]��	��R�dQ���jp�kz��n�KFͯj_'/�J��m��L0te��u���f�K��N�(�)䵈n��t�k:Gǆ�ö�A�NS���y��i�}��wQ!@�=\X���΢b|�c�p�w��೹��Y����N˄��'R*����KAxw�|Q�a`t�e�n�o���;#H�_�Z�	��߅���}ۆ��=ll��"���!|�4`��=jԥQ2�șW������/���<��>�>I�P�C�݀ǝt�XU�����`���b�8��`0h̤R���,��<�屛�D8����ƈG���������(��DT'ZP���,���CB�aū�6�nz��98	ⅺ%�rE8���;=>�8ſ<����×�؀���\���X��lu7+8�m�w$��̯��6��eB�UJG��a΋!��p5��=�/ S����O�f<��D�|W�����+�}~ɫ��0n�9YUq~6�<��8V�oi��*D���l��}�|�#$ߕ�=@V��7r�J����e�k��/;����E�ui�֘�K,E5�d���g�2k9���[j6�S�����St���KF�����r���U�gա�����ʛ�$�ѡ'��g�N�+9� �E�>$P\��k���yv�z�Yx�-��Y�$�� ���Ki�!O���T��h�$����qݲwh�z4.H� )�#"�{�w����"���q��~� �˿D�g�R��e��Ck���X?|k)�=�h�P�?�[z��(���M�dY�kN�����p: )7�/���������6)�� `���8�p��|�Y�(>��.�=@��"�Ӫ�ewƟ0�B�>�frݗ�
@�t��/�>�^���'���ޕϐ���͋��;t�%����֩���|���GGt&?��T���x�(G�����<�I���DJvS��V�K^�I\����uؒ��¼3F�1��{�} F���c�?%�̪nҨ��������r����Jk�-��]�o���4�E��m'�,�9�	�V��|	�J�Q�GɨD�DY�f+c*à�n/����v�b~�V�&��/\���	;�F��jK���~J7̥��V!��R"3�Z�
<!
��:��J&;|%����6%>�}*�Z������̀gqw��-B$T��+�j����B���w���}���!��Q���F�I�\�&k�@��&��4���#.dw��GF���?@��ctxe>]�Nr^V����]t�7T��Ay8|a�bMw)j�T㿆2�(4v��S�&*�7���W �:���l�P��5�!���8%�W��O�'/�y��A.&��,G����&(����F�?���:�
%<b���C0�)�cb>���o�,��ew�ޖ=2�t���u`���A2�����?����a�W����7�������TN����v.��oe�d��c욶�� 3  �o�)���r*��o��1D���u�e�|���F�B�sY�k�����f�p�L�k�lx��5�Ɵ�E{�id���A�]�غy֠��#_}�Y�~E�7E}�_k��u���^њ��� (w��NT�	D���P�(����O�(HG"��e��\9��v:��	L���qU�<,�VsR�?��3qu���e�:��O�}�j*��m�zP	)-���t��;|�]��~K���]�X0��ú�u;C���k���"=&����>��5k�~H�H�Į� �������$8�%�,ILm�o
�_��ӟ�����]      �      x��};s�H�����N�)K��du���[���;%�	&QDl �nR[�V;������Z;-��}�{D I��fת�@ �����<��d�i���U�v��+�K��E�B;���*xo��ޅ'�����;syi�cy\�ˡ��wfW��aq�Y_������t3&	���ux:ܛn?�� ϊ�D��JZ�˃rn��U_̦���[������*�qk��:�
���eg�C3������3<
�6ݺ���Ssi�fF����?��=��)(q�_=4k�����485m����~�����x��<H�ȵQ�4�eP̘*H}[)�Th+
�LZ�ĺ4���H�~�_��-w��AY��`EL���ﻦ3��Ayp����մ�8Ë��i��}������JLJ��T�OW��tnG��|n���~}S߇c9��uS�X?�*��&�. $�Œ:X|����3 ��u�[~�A>T��������z�ˠ2�l�l��}�p���fT|���C���6����</��
ߔ){d���Jڂ�횸vB|:���m�m;�C�D�e���_7P�Lw*�ko6���k���,�9���W��j0I���U����܄/��7�]��@"|6W$�OfC��#�(8��uxav��gx��ɔ��16@�tz�0y����t����Q�ϋg��"��eSo���"���T�v{�. �� 1b�q��Sp����9��Fa.�͍*��ܚ)Y�KC09Y�5
��k�G�!S6)�a+'�=_��n�`ђ�-��ĊĹ�H�؏ȕ���Nڵ!U�ʷWQ��o�?�5��j�X���?����S�K�7u��f|��iT�C�|���4�����d_�ڍP�mcw����b�����s	���~0�?�@��u�sg���'wx��$4���vߴ��a��W���%�X���_,�C���c�7s�}���n���W$�Ͳ\'s�ݦ�F�nd�B��(�e�쎡Ip>������8w��|]��v�%��.yu�s��)��Xeis��l���*8�<�d�dH�y#�h�`�Ph��r�$8k�m����ߡ���Q�>�u���u�b��s��.q�[���7�T�C弚������X�?n�]W�sWo�&P��a6��w�e�@��
T�����u�L��a�m�m�j�c{��@��0HT`�<���m��(fL}���1�0��e:��[��qX�˩�8�l� OU��f��S��	���	K����;����"�#l��S���c�s3~�����;��*���.�0BVZ�"���� t�"� ������P3��P
a����ӂ�sh���5�(<"�a6�����R������S�rj��4������WL;pO�� {�oǝ*�!�_��~��=nTS@˼�Y-�B���&�/`��u�&�o<g���q|hf0�\d������f�Q�=����bU�j&�u���I{V�Dx1W*�M�;Ц���)L�\m	�� ���1�:*4W�[	�SZ�A.�Me�̩�6�Ɋ�
yu�@����BfP����L��) #s�}�>�̢*H�B�Q���$�
o
N"���+�3R��e���d�e$(�	�5:�U�cb�JJ�z�d��1�
��(�-��t��wx�?|�c2Hj8?�4�-�C�X��J�bJ4߀P���Lۉ�F�������*�P�Z�T�ڎ�/��FN� j�N�	_O���ǩ�4�e*xpU��9<_��E��������|�:�K�Àx��-)���)%(���فV8,F׆�=���7#��k�#L
r�%7;��}g~fh�}���a"�#�Ħ�N�����]�i?��Q�æ��M�߶x{!o�erl�W��kJ�.i�~���]XA6m�����۷o��R Z��%�N�E?�-v�u��y+�*�<1��J�e�F��|��-:��N����m![*���5�끤nB:�QQY����s_��n��YP���a[�?�>�v�wA%<�{r9`v�<�o�1I��k:���+h����4x�_w����F���f+aȅ���J�*��Ϛ�Ə*��윅V����G_�J
X!_��>м��[�]'�ȯ��v=�sW��+�,=ؠ��98;B�����|���#<6ݏ�U���@�s��٠�`�I?��4ʉJ=�#�B�.�l��K_�p[N��n��@��]�·��6۶*᠜�Ҧ6w�4�.4)���o'���2�ڬ�=��A*02tU%��q򰯵lA�p�[��������i�#�u�?���:�\����t���@�rZ���������G��{X���@���/!��Us
�i٭�¦�b�'��1b�z��aJW�-�SVB:T�)h�^���M襱Y���.�%;���|DT�ԕ,FE���H>�Oa|���xG���c�&f@�O���4�,eJT��x�L�rS�dJ�G&�ƈ�N_��J�r�b�`(��BSV��Ij=��M�B�'�wJ+50��0f#.\µ�$��JJ�N	�%�؍�I	��O�)Țp+��H�%cD�x��ɪ*)�"��Kʉ�U�`]�V1���c~�L���/0k1l�J.O�a]�3�2�"��T����r�*zFTGb�����t'�
���p�g` ��6�f��~���8p���'���i=�:uhFj�0��l��́�B�f���/Ȍ�ܦk<$�۶K��ijӊ��	�bΦKa?J�E'�����v���W�5��4�B����F$�69��w�����gX�6�ap��
"1�S�#_�ta�$8�ǰu}���GB��k˂/�k���w��}���܀�Y`�V�����0��׾Y-V�����x��)L�W|�#X�4���%p<BXk{��"i��i=*��ti��#c�6`�^�D��~9���EgOğ�vr�l���}���Ѐ�ߛkK�h��ADw���8�
˿6��{�<1Xߎ�8)�/S!�&��4xe��#PKch����Ҋ�]I!`��ֽ��&�z2C�*�Q����T>�K�p��=f� <"�5�ȹG<S��$��M��ǳV!T!�`@˃�a�j����ܵ��*� ��OȞ���}�t��y)��B���w���zp�����f
m����T?x������Kia��;�E0�v×u�`�(���bA�T
A~��(�;VKF2A9n,�1@�y��&�%��e�.���>ԍQH�c.a�Њ�������iQ�T
���# �_� 8PD�S��!4<2�nⅦ%SL���)͎�Ɓ�})+Bԫq/,�f�G�,��d�
��&��<�x.)�+��8)�k&�V1�E���)�j)�_&S��R�9�bM%�/��*`�RS $n.�������jUL-�%�!�t,�\Ɨq)�l�)klK3	�^%�Nm�>N��	*������\���)�4�Y)�4*I��2�:��D�"�\@��xE�̤b�#�O�FJ��!�M�&2�@�]0���,�c�$�y����#�5��S�#�DA,�E.+�qb�4R�X�{^��E7î
���hҜ+��~̱��(�Q2,J*�bp�E���B�If���tDj�X	�z��b�J܇�ވ��eR���c�i�"SL�PqǃX�F��H���Q��$
�DB��K��ɝ�ep��#�A�Ҹ��gppO������m�(h����4�C��Ͱf����A���S1����W�a?�=�:�Ţ|�wuއ�0.�����/!�fP�1���D�pI���Z�����&�a�*{~R�����_|�S�9�ȂV&�֯��.�Ũ躩;�����/!��Skv��$[_/�w��}���?���~S�"
���� �؇�8�׵�<j�ʌ��x��CL�k3l��^�ލ�?����7��ג6w@3ᦹ���x��	ϕ�!a.��Q����	��f	��
������e��8��=�E�݅i���~�m�=|�x�QN�    (H�C8�]-`[_:|3m������o?�H���]���=� f����C��i \�����}���?l���Aˠ\�6���z}3c��݋�
�e�%,���4�g�L��e��})f��f˰'î�y�(��y��E�3�y��Qp�5�kJEȾY�]���ךi����ӈ5����!#R�L��)<��iX�����r��l���0ا���JG6�](�A�?�}{%Pg�\bAY=�w�������]���;���1�J�եb���&|dc� ��t;7��
�O��U~��NkX�}3�X��`�����C���1���CJ)p~�Ր]#�C�����z�����NF����������Q�~7�!0�T0<�����D�nz����PRO��b �=׌.	��P���YD*	`�ۘؕ�	���u&f"��z$0wy�`�R�Ҁȿ���zZ>+j�z"M-Ի��ᗾ�i��T0��8{q�-�)��7��w/@���_ɐ3<qL"c~��f��KYD*"`���%��g�B:RV�	��Y�SM�0%���:�o{7z��]������UTd���z#;�	���~(h��&Q���b�;�A���+�����T������H����B���F)�J:��@+o)SК�?�������kZs�[f���������v�E���9JАT�FhقB�F�,KI[	����/p[k�)EJ|����D��rE/���Q㻤���D];���I(�y�D���W��e��O�!��5Ib2=�>A�@Ѳl%���g6U�%�9��P�sK�g١ϴ�nsb�A��ā��b����;~p&�*��en� v��N� 6®����̮��7���u�N�<��(u�G9e�9,a�qF�)hO��f�}�B��i�����9Co��B^X��\ӑw�K&��Y\ղ�
�@,P{�r"���uk{������00�#`���r���n�,iG�:e��J:eZQHn�\��ֺ��J@��a�6gq-	�D�)V*��Zl��! 3�v���bŢϛ�n	H���CR�mwX?�-!B��'��7m����~�eb��uQiK9�@Up�������o����F&��X�xv�NB��ĕ5xX���Ă�װa��ִ$�@�Z��۩�p�ς�i Ǆ�=C���[$S�V� ���}�e��) ���m���">�G3�]}� ax���P�&6	N{�[m� B]jm?)�I!�|Q��r���S�4��g�vq�˼|Z��FU��J/��jцU:H�.������>��(M!�|nv*��-gA"3x�wɥ-f!#瓨U���}��Cմ
リ���n%닦�b��] ��,]r*�4�%�K�	I��x��5��g�&3N��
��������P扤y�6���d���y����J�������U�]BTu�^������,_"�t�����&E��P�|�d:i�M���%R#H���wo�X�;L�v�>tj5��ڑ3�����8]�b��a�Q��n>c6$ڏ���@��W�`�}�@��g�>e�� �t�������:��|��?��C5�$樤o�;�H��z
[?�����z?
ݡ�O ��� `�N�Yl����U�(�.�Μ���/Y	�����I6XۜN.���o�p84B �^�&Ň���)Ĥ������~:��B\���]�ɔ�Mw��]�O0�?6��R�"eE?AmjauI8ҋ=�%+����b"����;��rh�װ�wݷ��G�M}iJ�C����g�W1N��N��Fk�^�	4L��%�]�e�e2=��(��E��� Y_��h�N$_�ެ�Q'R�HG��̉I���[��-�;2�~�/����C���Sq���8�`�L��5�І��wApڅ�%R�H������+�h抩BQ�� ^?~}P�XP�WUf���>�PK��Q|�'%h�_͸�,�k#3U��3��H�#��O���H��4]��B��}�2`tc���f�����ͬX"E��K/xơ�Ի~͍�Q���M��K$�M��&�A��54��DV��h�
&��y��4n�q�G���8t�����H���C"Ix��R�`��$2ߌ��$m�Hf���俄�yP�U�a�7�^T�vjXϰL�I�9��;��\�`Ii�G�4�tɍ����]�r�����D��<Pp����A'V���%D�<Xaqp
��4P�#�VL#ɥW,ce�:�԰49�$ k8��}�h$ޢ�����ԍ#�q�I�,I%T��!��S��c���zC�	�z�EJ�*o$�N����������m�|#Ϝ��),�A����%dɶW4mT�KJ�٤���|#���l/���1�uK.�$;I��aM@� <�u�E��s����-S�P�!ɞTK7�z�e�0,\�5PL�}���T���J����H$S��Q�9D��S�cNkj�B�����(��
��`�3 ��&�u�ҊDK�W��]�0խ�{�������xT�����'�jN�����ZO��U��a��l/ؖ�	Zj�At�v#�hcv�JPr���m�R�+W��u��$�W�!�_�,�!yu�S<�j��B�L+��B����{��c�x@��>|��p�g��^�#Sl9c��%��<�ܬ\-��Fq��� W,Q����.�&��6>¾n.��=Hk�W0K����="}ԣ5�:ˊ\x���)����DK�Wqa��.,%��d��/�U�o��/����+�v�l��x�>��rX�`�#�����RpJ*�� Oͤ�HJ�|�<x-ޙk�:Ҵ�
����ɪRHN�(=Zo9�H&��LG!^ڣ^1�ZX�=&�@$���>�3��n�g�-Mܡ�����`Mh|V�O���Ry.�#�c�ƴ�@�I�$���73|Y�さ�\��'& ��T��(0��������Zx�H�^�t7�6[y�\��>�|�3�zC[��<��IJ�CXs1����� ��cW����R�_���PE�*`l�ң�Tƃ�q\���9��_� ���))��a>|6�<$��R�xx�LE�G�OV@9
���rt�'^)���iA�,�(k�(�גR9���j=8^,�%sWR*���ʞx��f��� �p��V3��J9�@�'#ӆ�6yL\����&�
(�����RٖJ%��=��/��R�����E}i�m(II�L*~�>������k���f�͘Wi�M�T���d����7;����r{)��n"MX�ލ*�6kb�0��K��P�
N<��E%�r0�B��.*��JY������8X�d1-�qݯ��r��!��pТG�l�n��>��w�dj�R$��"��g�@��	��	�N��lБ,�z$"�'����G�_�VM�2Tʡ"V�{���eϱ���G�#_{��.���,i\�j)�?b���=y�p>α��K�K	.�����M��+b���@I��#�kA�@	Aq�}zk����75���	�.ޞI���x�-=�'�c��y*���~]X��O����!H���c-����p��3#t� J϶�&H�H-b͙�1��Bn%�H�G��ָ7wu�csu�����\�a��עBS-jb=ȇ��ie�.�J%b����o�݌��O��<r�[���?)H��e����E(�V1�Nถ�wOr�Zk$�ՎJ?R�lb���>�!�@<���F�V�|��ic�T�H)�w}�Ϗr�V=���Q�ۏ���萆 ��aU/�_R�Fb1��
�ʎ(Ճ%W�3�����k��F�������a��q���T���=�:�e=��P�3EE?}��}gU��W�Q�s�/�L͊��1�R_Y<���\%O��G,��J�%�σѾ,r�Sa�����zB�?Sk�j��ӹ�y���1铢�5p���[�@�x���&�r&�{Rd�ja�=Sd䱪���oF�����8����M����5� Ւ&�n�k�    ���V02"��-_��0��i{=��'��!ң��Tk�x⎻T�����tν����M����}��[d�X�h�����V8���cadP+���G��~�Ɣ&�\8�;
`-Ab9�Q�Z��M,�u�v/�8��z��c��K3��3EQ�^.�a���<�<k�!��gf=�
>��B*_u��9M�0�JJ����;~i��`8Q� �&��p{����GF@�%L,^��!�txY����ܘ \�Y���3�t��<5�?̕\��EK,3ƖL���ꇿ}��j�SE�0y�͌^��0�~��?�`��ؙ5^�v��5j��`�#���G�D}:D����b}i6����X��KZDB0����pG1���0T�Ѵ�����0n��{ ���5��BЪH�Ke�#�e�Z�\#�2՜�뗰����ofh
�r��sK�hR�3u�����54G}�m[�&���/3��N�~0�(�7��������4���t���n�Ď�[���0���4p�]��^��5�@s�5�@d��ɡ5��L?�&�P3��[���0�w�To��KniVN�v��1P�ͮ�t���m�WRjI�rˌ���{��G��St,��/�~P�t����5���7���`\b��;�
-Y8��u������qy��v#���R^���N�J�5���o��	�W�l�E��y�9���T��\?��oEʵ4�9(I|��8��Gζ��VK����Rj����l.�
Xޑ�ys�s��f��vZ����HZj/�������sV�
��VO��bh�C�p��Q��膡��2|y�R���t"��{&w��	CVkX��;�L���ZM
��W�}T.����)����Ek��"�i�9~�7*&�74{#7Z��8���t ^R��mR�7��9<J�wl�W��H�#��Ktx2�
��5��Ől�9���.�2옔���IE�r���E�
���8�+:o$�}CI�0<�+ރ��yp��To��A��fu~������+A�`�lan�z�x6Σ�����]��F����ѩ���;鄈J�dj���N>�ԙ�N��u�ѢdD�����u��0@u�r1����;�Oi�"h�o���ҁ⧢H��r��#_�n,#��Z����kgfL����9=�$}װ�$�)t�h���ǚ�۬��Dǃ��6
��TX��K����P�)i������T/"�f|W|�il��i=�a�N�4Ly<��)͕�@q"�!����Tm?�ک�G�%?���?�>���h>��a1+�,T��@6�����<��Ns�3��u[��ǿ}��NWCY7���"�AF:����s2]A�揷Xf�v��\v����z�Ϫ���]x�y�l/R`��z��X}�+k�b�,(qY%�p�� ��J'[xh:-�:G~�����c!�L?�^0��)��zO���!Y ���L�ؗ�R��(��{a�Ӟ�Ӗ��wM�4�g��c0�Y?��Q=]�Iϡ{dn7Od`�	g�w�Ww���T��<�z�7�E�*�L�kT���ʯEa/��Z8���<6�ź��������B�Ts�<"����ţYk�������������Y��^���fl��v�E�,-�K�$>Mg�L�yI�'��gƳ�@$v��y�aM���>^{�hn%�\����Gދ�j�8[͜�y�6��ή@��dE�=��˅�jM	�y�6˗�����T��,��B*���N��MՅuR�g˒�鵆�xo@-�c%���;?a`Jl�yHS�Y�yH���эE@��o�b-���V������[9M#g,!���M��u��(Ѻn@|w��捐�|3V��c�j�����
I4�lx����.�<��fr��\� ��o�xз^��%v���dA�z��i$7�x�n��4,�e>���^�xPy��Lc��T�[��않��y<,����p����x2�fyr���b&�x0�/�ע����ֈ�Ȝg`�?p�2 ��)L��3�d	l��E�\u�q�������S}��ʭ�U�"rR(�U2�f�w�`�����}$0{����%o����7^ed�͊�r�<g"�j�p������k�O�.�=�TDs����L��Y�lRKBh�jx����WO�[�B�=��x�I��Gy�Tu� y��fe߉��`b%� Ӑ*�xL���-�����b:
��z54ߤX5��)�%�f�΍8ɺ4�+�1Y���1ƴ5�=�$㕟��.��'(ytn�&���a����>���13����i�����[^!r=Qȱ�L㪼�ଦRϠn֮�1g_n��#{�i���R�j>{��3���⩋��}s�3��+օ�Ҕ�W}�G�ߧ Є/5�4֙G��Lc�96�S���=$]�Nf�1�O`k��&�-���ԇp�`~�A�h�������OkX4��Ƌ����V��r��a6VR�zd�ؐ,<	Z\��	�iD3��S{�+u�T���Lc�yL�^�2s��#��9O~�Z���/�bm�rK�Jy\�-c���j?H��'Ox9jZ�;pc��>�z�nsʖ.=�d����"6��$Ox�5H�b$O�:� Ւ��/���9��gK'�������@��[m�|���Kt>i�-�O���f�������ۥJ�֡ͦ�C�k9t��g9/N>r�=������Ll�<�|�&֪����Z�!�-]�.�ȱݲ,;�A�Z(�f�b�^�ش,j��^�a7?{I��l,���Ǳg����<t�ˢ�Cs5�5��ײ��f��o������p^���_����<��>hD:�t��n�Z.��CU��C�x���d}B�H�?�-]�B�v�eUT�zׇ��/��`F�Ϡ�mj�e	�����c-��N�fn���ܣ�@��-%^^��4kX^j����~�*�N��=(��R�w����X�,G�Z�Kt<:P�E�ΟD�b�ۼ��QX<�J.^�������8���(��x�)�1�y�����kv����lk�J��8���I�n�7f��y����v;��a����i񮞋~j�6�9���{r���R�v�
��5�+����_�8��+VW��ŕ_��^P�6�!�^����*�U<�b9ZqU�bF7Ҷ+��
��� I�Sx�tW��$�*b�,�	�ejxc�1ZF�6��-r��0{<.��#��^c�H�i�U=k^<�+aA��V�v�-�hc��n�v�=�;@���6w�%P��"N���Q��
h꿬�t]�'�����:�?��%	@��=R����@�T�_qP���O�rY���W��喗TnI 
dz?�&��
̕	�{���p��0�OXE�����f*' +9@�ת�}�V���hm�}x:I1�C�q�Mͤ/�����~Xt(��ݖT�` �:���4V�KW�W[<@N���:<��Ib;L�0?�X��Ʊ��G?����Ҽ��'a�����"�;f8beW�f�k:ɓ�ʏ%��KZ�!n~�$Ce!07x��2)L�B���ln$A97xb:�0���6[��(�ե�/�Z7�.�h�^���M�h3ռ�&���Pnd_g�m���Ʒ ,4�^O M����Ǚ�D�"g�|���`�����-y���:�y3�|���2���<o��^H�#j��|=G�;JK��
���U�oy��.w:Q���D�(��|p[� Gd〥��Cyn�f��2D!E���o���8og<c�j9��m��e�-�w��οɖ&Jɰ ��)�4Q�/��?��f�YbB�慁i�\!��&J�%��7*�m�� ���D����D	f��I�`��R����DYCo��	/n���@����<J��HZΓV.`Y��������jy���{t|.Ł�^D�n����J~,�۩�	��rɥ��?�O�}>�������/x�S��_��L�*H�w�o�n{V��">60?���#xg�=�D�0x�ӝ��T����z�]��d����S�#h�-�T��"�7���G�+x�S�'p��<�7�
���~� ;  tt�<�|�[��GAYs�r��Q�H5O��q�>=��A�H5E%Y%{ݮ͡�H�L����n�;����з݆v�J7���j�w��� nzJ�Hu�m���H���٫��&�9яx�Y����K�Z����a��EN��m;_R��񲤍Y�孼ݐ)�In9�Hu�
�h�$C�^}�)�q�
X~�%R��- �?~,\"�<1��j,�R������b\/&�n���D�E�G7�=�߁����hn��f@}�g���Q�����.���w�F��S3�_k�o�b��0Q*1ψz:t ���Dc�+)Z�����1��xy t�^�̉�HTY��O8ȍ�5ckZ�׸��p>���%:|�_63.=µ<I�X�L_�u:ٛ5/6�ϰbf�@��Y�H�T,�C����_�i�Z�����y\Ԏ>��!�k�����6�����c�����,j1�!R
�}O����CsY�h��G��p�D#�<AJ���[?p���QU�(��'�Q����ނh�c�;��2d��	|i�Y��u���*����s�*���g�<#俽���/�ˣ�      �   [   x��;
�@ �:9��KQX��M�()�H6�ߵ{L	�p<���E��e�X/�3��ќ��U���5�f~��=lo��p�@�M��y�D� 
��      �   !   x�3��/H����2�t,(ʯ��Msc���� ���     