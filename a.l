
  Weed {
  Angle 50
  Axiom +++++++++++++x
  x=f[@.5+++++++++x]-f[@.4-----------!x]@.6x
  }

  Alien { 
  Angle 11
  Axiom x
  x=[@q2@I2-fx]g[@q2@I2---fx]
  f=
  }

  Trigrid1 {
  Angle 6
  Axiom x
  x=fy[+fy][--fy]fy
  y=fx[++fx][-fx]fx
  f=
  }

  
  Grid { 
  Angle 4	; Allow turns of 90=360/4 degrees
  Axiom f	; Birth string is the symbol f (see text below)
  f=f[+f][-f]f	; Replacement string for f (see text below)
  }

  
  Hex { 
  Angle 6	  ; Turns are 60=360/6 degrees
  Axiom f	  ; Birth string is f
  f=-f+f+g[+f+f]- ; Replacement string for f 
  g=gg		  ; Replacement string for g (see text below)
  }

  Isocepent { 
  Angle 10
  Axiom w
  w=++[-@1.618033989f@I1.618033989++++f]fx--[++@1.618033989f----f]fw--
  w=[--@1.618033989f++++f]fy++
  x=-[+@1.618033989f@I1.618033989----f]fz++[--@1.618033989f++++f]fy-
  y=--[+@1.618033989f@I1.6180339898----f]fz++[--@1.618033989f++++f]fy++
  y=[++@1.618033989f----f]fw--
  z=+[-@1.618033989f@I1.618033989++++f]fx--[++@1.618033989f----f]fw+
  f=
  }

  Terdragon {
  Angle 3   ; Only need turns of 120=360/3 degrees
  Axiom f   ; Draw one Terdragon
  f=f+f-f   ; Replace with the Terdragon, turn left, another Terdragon, turn right, another
            ; Terdragon
  }

  Dekking { ; Dekking's Church
  ; Advances in Math, vol. 44, 1982, pp. 78-104
  Angle 4
  Axiom C32WC06ZC15YC06X
  F=
  W=FW+F-XFW-F+Z
  X=++F--Y-F+Z++F--Y-F+Z
  Y=++F--Y+F-X
  Z=FW+F-X
  }

  Pentive3 { 
  Angle 10
  Axiom q
  f=
  p=--fr++++fs--fu
  q=ft++fr----fs++
  r=++fp----fq++ft
  s=fu--fp++++fq--
  t=+fu--fp+
  u=-fq++ft-
  }

  Cross {
  Angle 4
  Axiom FX
  X=FX+FX+FXFY-FY-
  Y=+FX+FXFY-FY-FY
  F=
  }

  SierpinskiCarpet {
  Angle 4
  Axiom f
  f=f+f-f-f-g+f+f+f-f
  g=ggg
  }

  Pentigree { 
  Angle 5
  Axiom F-F-F-F-F
  F=F-F++F+F-F-F
  }

  Xmastree { 
  Angle 10
  Axiom ++p  ; The +'s are there to make the tree stand upright
  f=
  p=--fr++++<1fs--<1fu
  q=ft++<1fr----<1fs++
  r=++fp----<1fq++<1ft
  s=fu--<1fp++++<1fq--
  t=+fu--<1fp+
  u=-fq++<1ft-
  }
