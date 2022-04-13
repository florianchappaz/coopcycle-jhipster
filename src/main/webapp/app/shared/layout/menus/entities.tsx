import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/cooperative">
      <Translate contentKey="global.menu.entities.cooperative" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/zone">
      <Translate contentKey="global.menu.entities.zone" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/city">
      <Translate contentKey="global.menu.entities.city" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/deliver-man">
      <Translate contentKey="global.menu.entities.deliverMan" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/customer">
      <Translate contentKey="global.menu.entities.customer" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/restaurant">
      <Translate contentKey="global.menu.entities.restaurant" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/meal">
      <Translate contentKey="global.menu.entities.meal" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
