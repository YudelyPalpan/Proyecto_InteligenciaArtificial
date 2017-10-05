;tablero
(setq tablero
      '(
       (1 0 1 0 1 0 1 0)
       (0 0 0 0 0 0 0 0)
       (0 0 0 0 0 0 0 0)
       (0 0 0 0 0 0 0 0)
       (0 0 0 0 0 0 0 0)
       (0 0 0 0 0 0 0 0)
       (0 0 0 0 0 0 0 0)
       (0 0 0 9 0 0 0 0)
      )
)

(defun avanzar-gato-derecha (estado)
  
  ;(format t "~&Tablero: ~S" (nth 0 estado))
  ;(format t "~&Posicion: ~S" (nth 1 estado))
  ;(format t "~&Turno: ~S" (nth 2 estado))
  (setq
     (tab (nth 0 estado)) 
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
  )
     (cond
       (
        (and (or (< i 7)  (< j 7)) (= turno 1) (= (nth (+ j 1) (nth (+ i 1) tab))))
        (setq (nth (+ j 1) (nth (+ i 1) tab)) 1)
        (setq (nth j (nth i tab)) 0)
        (return (tab (list (+ i 1) (+ j 1)) 9))
       )
      )
) 
