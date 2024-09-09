CREATE OR REPLACE PROCEDURE persistencia.promover_alumnos(
	in idOrigen integer,
	in idDestino integer,
	out nro_actualizaciones integer,
	out nro_eliminaciones integer 
)
 LANGUAGE plpgsql
AS $procedure$

begin

	update	alumnos set
		id_curso = idDestino,
		nota_alumno = 0
	where	id_curso = idOrigen
	and 	nota_alumno >= 5;

	get diagnostics nro_actualizaciones = ROW_COUNT;
	
	delete
		from	alumnos
		where	id_curso = idOrigen
		and 	nota_alumno< 5;
	
	get diagnostics nro_eliminaciones = ROW_COUNT;
	
	exception
		when others then
			rollback;
			RAISE NOTICE 'Error al promocionar los alumnos';
end;
$procedure$
;
