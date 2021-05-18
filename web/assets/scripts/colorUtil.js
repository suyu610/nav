//http://stackoverflow.com/questions/2353211/hsl-to-rgb-color-conversion/9493060#9493060

function rgb2hsl(rgb) {
  // arguments: [r,g,b] or r,g,b
  // return [H, S, L];
  if (rgb instanceof Array) {
    r = rgb[0] / 255;
    g = rgb[1] / 255;
    b = rgb[2] / 255;
  } else {
    r = arguments[0] / 255;
    g = arguments[1] / 255;
    b = arguments[2] / 255;
  }
  //r /= 255, g /= 255, b /= 255;
  var max = Math.max(r, g, b),
    min = Math.min(r, g, b);
  var h, s, l = (max + min) / 2;

  if (max == min) {
    h = s = 0; // achromatic
  } else {
    var d = max - min;
    s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
    switch (max) {
      case r:
        h = (g - b) / d + (g < b ? 6 : 0);
        break;
      case g:
        h = (b - r) / d + 2;
        break;
      case b:
        h = (r - g) / d + 4;
        break;
    }
    h /= 6;
  }
  var H = h * 360;
  var S = s * 100;
  var L = l * 100;
  // 'hsl(0, 65.2%, 91%)';
  return 'hsl('+H+','+ S+'%,'+ L+'%)';
} // arguments: [r,g,b] or r,g,b

function hsl2rgb(HSL) {
  // arguments: [H,S,L] or H,S,L
  //return [r, g, b];
  if (HSL instanceof Array) {
    h = Number(HSL[0]) / 360;
    s = Number(HSL[1]) / 100;
    l = Number(HSL[2]) / 100;
  } else {
    h = Number(arguments[0]) / 360;
    s = Number(arguments[1]) / 100;
    l = Number(arguments[2]) / 100;
  }
  // var h = H/360;
  //var s = S/100;
  //var l = L/100;
  var r, g, b;

  if (s == 0) {
    r = g = b = l; // achromatic
  } else {
    var hue2rgb = function hue2rgb(p, q, t) {
      if (t < 0) t += 1;
      if (t > 1) t -= 1;
      if (t < 1 / 6) return p + (q - p) * 6 * t;
      if (t < 1 / 2) return q;
      if (t < 2 / 3) return p + (q - p) * (2 / 3 - t) * 6;
      return p;
    }

    var q = l < 0.5 ? l * (1 + s) : l + s - l * s;
    var p = 2 * l - q;
    r = hue2rgb(p, q, h + 1 / 3);
    g = hue2rgb(p, q, h);
    b = hue2rgb(p, q, h - 1 / 3);
  }

  return [Math.round(r * 255), Math.round(g * 255), Math.round(b * 255)];
} // arguments: [H,S,L] or H,S,L

function rgb2hex(rgb) {
  if (rgb instanceof Array) {
    r = Number(rgb[0]);
    g = Number(rgb[1]);
    b = Number(rgb[2]);
  } else {
    r = Number(arguments[0]);
    g = Number(arguments[1]);
    b = Number(arguments[2]);
  }
  var hexR = r.toString(16);
  if (hexR.length == 1) {
    hexR = "0" + hexR;
  };
  var hexG = g.toString(16);
  if (hexG.length == 1) {
    hexG = "0" + hexG;
  };
  var hexB = b.toString(16);
  if (hexB.length == 1) {
    hexB = "0" + hexB;
  };
  return [hexR, hexG, hexB];
} // arguments: array [r,g,b] or 3 values: r,g,b

function hex2rgb(hex) {
  // arguments: array [r,g,b] or 3 values: r,g,b
  var rgbRy = [];
  if (hex instanceof Array) {
    for (var i = 0; i < hex.length; i++) {
      rgbRy[i] = parseInt(hex[i], 16);
    }

  } else {
    for (var i = 0; i < arguments.length; i++) {
      rgbRy[i] = parseInt(arguments[i], 16);
    }
  }

  return rgbRy;

} // arguments: array [r,g,b] or 3 values: r,g,b

function hsl2hex(HSL) { // arguments: [H,S,L]!!!
  var rgb = hsl2rgb(HSL);
  console.log(rgb)
  return rgb2hex(rgb);
} // arguments: [H,S,L]!!!

function hex2hsl(HEX) { 

  var rgb = hex2rgb(HEX);  
  return rgb2hsl(rgb);
} // arguments: [R,G,B]!!!

function hex2ry(hex) {

  if (hex.charAt(0) == "#") {
    hex = hex.substr(1);
  }

  var hexRy = ["ff", "ff", "ff"];

  if (hex.length == 6) {
    hexRy[0] = hex.slice(0, 2);
    hexRy[1] = hex.slice(2, 4);
    hexRy[2] = hex.slice(4, 6);
  } else if (hex.length == 3) {
    var r = hex.slice(0, 1);
    var g = hex.slice(1, 2);
    var b = hex.slice(2, 3);
    hexRy[0] = r + r;
    hexRy[1] = g + g;
    hexRy[2] = b + b;
  }
  return hexRy;
} // argument: "#123456" || "#123" || "123456" || "123"

function rgb2ry(rgb) {
  // "rgb(255,100,178)"
  // "255,100,178"
  // ["255", "100", "178"]
  var ry = rgb.split(/(\(|\))/)[2].split(",");
  for (var i = 0; i < ry.length; i++) {
    if (ry[i] < 0 || ry[i] > 255) return [255, 255, 255];
  }
  return ry;
} // argument: "rgb(255,100,178)"

function hsl2ry(hsl) {
  // "hsl(255,100%,50%)"
  // "255,100%,50%"
  // ["255", "100", "178"]
  var hslry = [0, 0, 100];

  var ry = hsl.split(/(\(|\))/)[2].split(",");
  for (var i = 0; i < ry.length; i++) {
    hslry[i] = Number(ry[i].replace("%", "")) //.trim();
    if (i > 0 && hslry[i] > 100) return [0, 0, 100];
  }
  return hslry;
} // argument: "hsl(255,100%,50%)"

function validateHex(hex) {
  return /(^#?[0-9A-F]{6}$)|(^#?[0-9A-F]{3}$)/i.test(hex);
}

function validateRgb(rgb) {
  return /^rgb\((\s*\d{1,3}\s*),(\s*\d{1,3}\s*),(\s*\d{1,3}\s*)\)$/.test(rgb);
}

function validateHsl(HSL) {
  return /^hsl\((\s*\d{1,3}\s*),(\s*\d{1,3}%\s*),(\s*\d{1,3}%\s*)\)$/.test(HSL);
}

function display_hex(ry) {
  return "#" + ry[0] + ry[1] + ry[2];
}

function display_rgb(ry) {
  return "rgb(" + Math.round(ry[0]) + "," + Math.round(ry[1]) + "," + Math.round(ry[2]) + ")";
}

function display_hsl(ry) {
  return "hsl(" + Math.round(ry[0]) + "," + Math.round(ry[1]) + "%," + Math.round(ry[2]) + "%)";
}

function hex2hex(hex) {
  if (validateHex(hex)) {
    return hex2ry(hex);
  } else {
    return ["ff", "ff", "ff"];
  }
}

function rgb2rgb(rgb) {
  if (validateRgb(rgb)) {
    return rgb2ry(rgb);
  } else {
    return ["255", "255", "255"];
  }
}

function hsl2hsl(hsl) {
  if (validateHsl(hsl)) {
    return hsl2ry(hsl);
  } else {
    return ["0", "0", "100"];
  }
}
