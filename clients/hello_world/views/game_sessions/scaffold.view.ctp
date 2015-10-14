<?php
/**
 * Bootstrap Scaffold for CakePHP
 *
 * @author        Fedotov Victor (xv1t@yandex.ru)
 * @link          https://github.com/xv1t/cakephp-scaffold-bootstrap3
 * @package       Cake.View.Scaffolds
 * @version       0.1.2014-02-24
 * @license       http://www.opensource.org/licenses/mit-license.php MIT License
 */
?>
<?php
$simple_view = true;
$this->model = ClassRegistry::init($modelClass);
$field_type = $this->model->getColumnTypes();
$id = false;
//debug($this->model->id)
?>
<div class="col-md-12">


    <div class="panel panel-default ">
        <div class="panel-heading">
            <h2><?php
                if (empty(${$singularVar}[$modelClass][$this->model->displayField])) {
                    echo __d('View', true);
                } else {
                    echo ${$singularVar}[$modelClass][$this->model->displayField];
                }
                ?>
                <small><?php echo $singularHumanName; ?></small>

                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        Actions <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                    	 <li><a href="<?php  echo $this->webroot; ?>game_sessions/initgamesession/<?php echo $this->Form->value($modelClass . '.' . $primaryKey); ?>" ><span class="glyphicon glyphicon-play-circle"></span>Initialize on REST Server</a></li>
                    
                        <?php
                        echo "\t\t<li>";
                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-tags"></span> Status',true)), array('action' => 'admin_status', ${$singularVar}[$modelClass][$primaryKey]), array('escape' => false));
                        echo " </li>\n";
                        
                        echo "\t\t<li>";
                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-road"></span> Advance Round',true)), array('action' => 'advanceRound', ${$singularVar}[$modelClass][$primaryKey]), array('escape' => false));
                        echo " </li>\n";
                        
                        echo "\t\t<li>";
                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-edit"></span> Edit %s',true), $singularHumanName), array('action' => 'edit', ${$singularVar}[$modelClass][$primaryKey]), array('escape' => false));
                        echo " </li>\n";

                        echo "\t\t<li>";

                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-remove"></span> Delete %s',true), $singularHumanName), array('action' => 'delete', ${$singularVar}[$modelClass][$primaryKey]), array('escape' => false), __d('cake', 'Are you sure you want to delete # %s?', ${$singularVar}[$modelClass][$primaryKey]));
                        echo " </li>\n";
                        echo "\t\t<li class=\"divider\"></li>";
                        echo "\t\t<li>";
                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-list-alt"></span> List %s',true), $pluralHumanName), array('action' => 'index'), array('escape' => false));
                        echo " </li>\n";

                        echo "\t\t<li>";
                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-plus-sign"></span> New %s',true), $singularHumanName), array('action' => 'add'), array('escape' => false));
                        echo " </li>\n";

                        echo "\t\t<li class=\"divider\"></li>";
                        $done = array();
                        foreach ($associations as $_type => $_data) {
                            foreach ($_data as $_alias => $_details) {
                                if ($_details['controller'] != $this->name && !in_array($_details['controller'], $done)) {
                                    echo "\t\t<li>";
                                    echo $this->Html->link(
                                             sprintf(__d('cake', '<span class="glyphicon glyphicon-list-alt"></span> List %s',true), Inflector::humanize($_details['controller'])), array( 'controller' => $_details['controller'], 'action' => 'index'), array('escape' => false)
                                    );
                                    echo "</li>\n";
                                    echo "\t\t<li>";
                                    echo $this->Html->link(
                                            sprintf( __d('cake', '<span class="glyphicon glyphicon-plus-sign"></span> New %s',true), Inflector::humanize(Inflector::underscore($_alias))), array( 'controller' => $_details['controller'], 'action' => 'add'), array('escape' => false)
                                    );
                                    echo "</li>\n";
                                    $done[] = $_details['controller'];
                                }
                            }
                        }
                        ?>
                    </ul>
                </div>

            </h2>
        </div>

        <div class="<?php echo $pluralVar; ?> view panel-body">

            <dl class="dl-horizontal">
                <?php
                foreach ($scaffoldFields as $_field) {
                    $isKey = false;
                    if (!empty($associations['belongsTo'])) {
                        foreach ($associations['belongsTo'] as $_alias => $_details) {
                            if ($_field === $_details['foreignKey']) {
                                $isKey = true;
                                echo "\t\t<dt>" . Inflector::humanize($_alias) . "</dt>\n";
                                echo "\t\t<dd>\n\t\t\t";
                                echo $this->Html->link(
                                        ${$singularVar}[$_alias][$_details['displayField']], array( 'controller' => $_details['controller'], 'action' => 'view', ${$singularVar}[$_alias][$_details['primaryKey']])
                                );
                                echo "\n\t\t&nbsp;</dd>\n";
                                break;
                            }
                        }
                    }
                    if ($isKey !== true) {
                        // if ($_field = $this->model->promaryKey)

                        if (!$simple_view || ($simple_view && !in_array($_field, array( 'updated', 'create_user_id', 'update_user_id')))) {
                            echo "\t\t<dt>" . Inflector::humanize($_field) . "</dt>\n";
                            switch ($field_type[$_field]) {
                                case 'boolean':
                                    if (empty(${$singularVar}))
                                        $checked = '';
                                    else
                                        $checked = (${$singularVar}[$modelClass][$_field]) ? 'checked' : '';
                                    echo "<dd><input type=checkbox $checked disabled></dd>";
                                    break;

                                default:
                                    $val = '';
                                    if (!empty(${$singularVar}[$modelClass][$_field]))
                                        $val = ${$singularVar}[$modelClass][$_field];
                                    echo "\t\t<dd>" . h($val) . "&nbsp;</dd>\n";
                                    break;
                            }
                        }
                    }
                }
                ?>
            </dl>
        </div>


        <?php
        /*
         * Related data
         */
        if (!empty($associations['hasOne'])) :
            foreach ($associations['hasOne'] as $_alias => $_details):
                ?>
                <div class="panel-footer">
                    <h3><?php echo Inflector::humanize($_details['controller']) ?> 
                        <small><?php echo __d('cake', "Related",true); ?></small>

                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                Actions <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><?php
                                    echo $this->Html->link(
                                             sprintf(__d('cake', 'Edit %s',true), Inflector::humanize(Inflector::underscore($_alias))), array( 'controller' => $_details['controller'], 'action' => 'edit', ${$singularVar}[$_alias][$_details['primaryKey']])
                                    );
                                    echo "</li>\n";
                                    ?>
                            </ul>                
                        </div>
                    </h3>
                </div>    
                <div class="panel-body">
                        <?php if (!empty(${$singularVar}[$_alias])): ?>
                        <dl class="dl-horizontal">
                            <?php
                            $otherFields = array_keys(${$singularVar}[$_alias]);
                            foreach ($otherFields as $_field) {
                                echo "\t\t<dt>" . Inflector::humanize($_field) . "</dt>\n";
                                echo "\t\t<dd>\n\t" . ${$singularVar}[$_alias][$_field] . "\n&nbsp;</dd>\n";
                            }
                            ?>
                        </dl>
                <?php endif; ?>
                </div>
                <?php
            endforeach;
        endif;

        /*
         * Related multiple
         */

        if (empty($associations['hasMany'])) {
            $associations['hasMany'] = array();
        }
        if (empty($associations['hasAndBelongsToMany'])) {
            $associations['hasAndBelongsToMany'] = array();
        }
        $relations = array_merge($associations['hasMany'], $associations['hasAndBelongsToMany']);
        $i = 0;
        //  debug($relations);
        foreach ($relations as $_alias => $_details):
            $otherSingularVar = Inflector::variable($_alias);
            ?>
            <div class="panel-footer">
                <h3>
    <?php echo Inflector::humanize($_details['controller']) ?>
                    <small><?php echo __d('cake', "Related"); ?></small>
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            Actions <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><?php
                                echo $this->Html->link(
                                         sprintf(__d('cake', "New %s", true),Inflector::humanize(Inflector::underscore($_alias))), array( 'controller' => $_details['controller'], 'action' => 'add', '?' => $_details['foreignKey'] . '=' . $this->model->id)
                                );
                                ?> </li>
                        </ul>
                    </div>
                </h3>
            </div>
    <?php if (!empty(${$singularVar}[$_alias])): ?>
                <div style="overflow-x: auto">
                    <table class="table table-bordered table-condensed table-striped">
                        <thead>
                            <tr>
                                <?php
                                $otherFields = array_keys(${$singularVar}[$_alias][0]);
                                if (isset($_details['with'])) {
                                    $index = array_search($_details['with'], $otherFields);
                                    unset($otherFields[$index]);
                                }
                                foreach ($otherFields as $_field) {
                                    if (!$simple_view || ($simple_view && !in_array($_field, array('password','created', 'updated', 'create_user_id', 'update_user_id'))))
                                        echo "\t\t<th>" . Inflector::humanize($_field) . "</th>\n";
                                }
                                ?>
                                <th class="actions">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $i = 0;
                            foreach (${$singularVar}[$_alias] as ${$otherSingularVar}):
                                echo "\t\t<tr>\n";

                                foreach ($otherFields as $_field) {
                                    if (!$simple_view || ($simple_view && !in_array($_field, array( 'password','created', 'updated', 'create_user_id', 'update_user_id'))))
                                        echo "\t\t\t<td>" . ${$otherSingularVar}[$_field] . "</td>\n";
                                }

                                echo "\t\t\t<td class=\"actions\">\n";
                                echo "\t\t\t\t";
                                echo $this->Html->link(
                                        __d('cake', 'View',true), array('controller' => $_details['controller'], 'action' => 'view', ${$otherSingularVar}[$_details['primaryKey']])
                                );
                                echo "\n";
                                echo "\t\t\t\t";
                                echo $this->Html->link(
                                        __d('cake', 'Edit',true), array( 'controller' => $_details['controller'], 'action' => 'edit', ${$otherSingularVar}[$_details['primaryKey']])
                                );
                                echo "\n";
                                echo "\t\t\t\t";
                                echo $this->Html->link(
                                        __d('cake', 'Delete',true), array( 'controller' => $_details['controller'], 'action' => 'delete', ${$otherSingularVar}[$_details['primaryKey']]), null, __d('cake', 'Are you sure you want to delete # %s?', ${$otherSingularVar}[$_details['primaryKey']])
                                );
                                echo "\n";
                                echo "\t\t\t</td>\n";
                                echo "\t\t</tr>\n";
                            endforeach;
                            ?>
                        </tbody>
                    </table>
                </div>
            <?php endif; ?>

        <?php endforeach; ?>

<?php //end of panel     ?>
    </div>
</div>
