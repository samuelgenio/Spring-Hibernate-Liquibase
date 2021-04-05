import React from 'react';
import NumberFormat from 'react-number-format';

export default function FormatNumber(
    { value, displayType, name, onChange,onblur, id, onValueChange } : 
    {value:any, displayType:any, name:string, onChange:any,onblur:any, id:string, onValueChange?:any}) {
  return (
    <NumberFormat
      id={id}
      name={name}
      value={value}
      displayType={displayType == null ? 'text' : displayType}
      prefix={'R$ '}
      decimalSeparator={','}
      decimalScale={2}
      fixedDecimalScale={true}
      onChange={onChange}
      onBlur={onblur}
      renderText={formattedValue => <p>{formattedValue}</p>} // <--- Don't forget this!
      onValueChange={onValueChange}
    />
  );
}