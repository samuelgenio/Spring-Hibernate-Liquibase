import React from 'react';
import NumberFormat from '../components/NumberFormat';

import {DataInterface} from '../interfaces/Data';

const ListItem = ({data} : {data:DataInterface}) => {

  function dateFormat(value:string) {
    const replaced = value.split("-");
    return replaced[2] + "/" + replaced[1] + "/" + replaced[0];
  }

  return (
    <li id="list-itens">
      <span className="Item-field">
        {data.nome}
      </span>
      <span className="Item-field">
        <NumberFormat displayType="text" value={data.vlrOriginal} id="vlrOriginal" name="vlrOriginal" onChange={null} onblur={null}  />
      </span>
      <span className="Item-field">
        <NumberFormat displayType="text" value={data.vlrCorrigido} id="vlrOriginal" name="vlrOriginal" onChange={null} onblur={null}  />
      </span>
      <span className="Item-field">
      {dateFormat(data.datVencimento)}
      </span>
      <span className="Item-field">
      {dateFormat(data.datPagamento)}
      </span>
    </li>
  );
};

export default ListItem;