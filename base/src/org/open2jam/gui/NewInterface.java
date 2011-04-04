package org.open2jam.gui;


/*
 * Interface.java
 *
 * Created on Oct 30, 2010, 6:51:02 PM
 */

import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.GZIPInputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import org.open2jam.parser.Chart;
import org.open2jam.render.BeatmaniaRender;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.open2jam.Config;
import org.open2jam.parser.ChartList;
import org.open2jam.render.O2jamRender;
import org.open2jam.render.Render;
/**
 *
 * @author fox
 */
public class NewInterface extends javax.swing.JFrame
        implements PropertyChangeListener, ListSelectionListener {

    static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private ChartListTableModel model_songlist;
    private ChartTableModel model_chartlist;
    private String cwd;
    private ArrayList<String> dir_list;
    private DisplayMode[] display_modes;
    private ChartModelLoader task;
    private int rank = 0;
    private ChartList selected_chart;
    private Chart selected_header;
    private int last_model_idx;
    private final TableRowSorter<ChartListTableModel> table_sorter;

    Configuration cfg_window = new Configuration();
    SkinConfiguration skin_window = new SkinConfiguration();

    javax.swing.ListSelectionModel chartLM;

    /** Creates new form Interface */
    public NewInterface() {
        initLogic();
        initComponents();
        this.setLocationRelativeTo(null);
        load_progress.setVisible(false);
        table_sorter = new TableRowSorter<ChartListTableModel>(model_songlist);
        table_songlist.setRowSorter(table_sorter);
        txt_filter.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {updateFilter();}
            public void removeUpdate(DocumentEvent e) {updateFilter();}
            public void changedUpdate(DocumentEvent e) {updateFilter();}
        });

        loadDirlist();

        cfg_window.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {
                dir_list = Config.get().getDirsList();
                loadDirlist();
            }
            public void windowActivated(WindowEvent arg0) {
                // Not interested in this
            }
            public void windowClosing(WindowEvent arg0) {
                // Not interested in this
            }
            public void windowDeactivated(WindowEvent arg0) {
                // Not interested in this
            }
            public void windowDeiconified(WindowEvent arg0) {
                // Not interested in this
            }
            public void windowIconified(WindowEvent arg0) {
                // Not interested in this
            }
            public void windowOpened(WindowEvent arg0) {
                // Not interested in this
            }
        });

        javax.swing.table.TableColumn col = null;
        col = table_songlist.getColumnModel().getColumn(0);
        col.setPreferredWidth(180);
        col = table_songlist.getColumnModel().getColumn(1);
        col.setPreferredWidth(30);
        col = table_songlist.getColumnModel().getColumn(2);
        col.setPreferredWidth(80);

        chartLM = table_chartlist.getSelectionModel();
        chartLM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                if (e.getValueIsAdjusting()) return;
                javax.swing.ListSelectionModel lsm = (javax.swing.ListSelectionModel)e.getSource();
                if(lsm.isSelectionEmpty()) return;
                int selectedRow = lsm.getMinSelectionIndex();
                if(selectedRow < 0) return;
                selected_header = selected_chart.get(selectedRow);
                if(selectedRow != rank)
                {
                    lbl_rank.setText("Overriden rank!!!");
                }
                else
                {
                    lbl_rank.setText("Rank:");
                }
                updateInfo();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rank_group = new javax.swing.ButtonGroup();
        panel_info = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        lbl_artist = new javax.swing.JLabel();
        lbl_bpm = new javax.swing.JLabel();
        lbl_level = new javax.swing.JLabel();
        lbl_notes = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        lbl_genre = new javax.swing.JLabel();
        lbl_bpm1 = new javax.swing.JLabel();
        lbl_genre1 = new javax.swing.JLabel();
        lbl_level1 = new javax.swing.JLabel();
        lbl_notes1 = new javax.swing.JLabel();
        lbl_time1 = new javax.swing.JLabel();
        bt_play = new javax.swing.JButton();
        lbl_cover = new javax.swing.JLabel();
        lbl_keys1 = new javax.swing.JLabel();
        lbl_keys = new javax.swing.JLabel();
        jc_autoplay = new javax.swing.JCheckBox();
        combo_channelModifier = new javax.swing.JComboBox();
        lbl_channelModifier = new javax.swing.JLabel();
        combo_visibilityModifier = new javax.swing.JComboBox();
        lbl_visibilityModifier = new javax.swing.JLabel();
        lbl_filename = new javax.swing.JLabel();
        table_scroll2 = new javax.swing.JScrollPane();
        table_chartlist = new javax.swing.JTable();
        jc_timed_judgment = new javax.swing.JCheckBox();
        panel_setting = new javax.swing.JPanel();
        jr_rank_hard = new javax.swing.JRadioButton();
        combo_displays = new javax.swing.JComboBox();
        txt_res_height = new javax.swing.JTextField();
        txt_res_width = new javax.swing.JTextField();
        jc_vsync = new javax.swing.JCheckBox();
        lbl_rank = new javax.swing.JLabel();
        lbl_display = new javax.swing.JLabel();
        jc_custom_size = new javax.swing.JCheckBox();
        jr_rank_easy = new javax.swing.JRadioButton();
        lbl_hispeed = new javax.swing.JLabel();
        lbl_res_x = new javax.swing.JLabel();
        jr_rank_normal = new javax.swing.JRadioButton();
        jc_full_screen = new javax.swing.JCheckBox();
        bt_choose_dir = new javax.swing.JButton();
        load_progress = new javax.swing.JProgressBar();
        js_hispeed = new javax.swing.JSpinner();
        btn_configuration = new javax.swing.JButton();
        lbl_dirKey = new javax.swing.JLabel();
        lbl_skin_selection = new javax.swing.JLabel();
        btn_skin = new javax.swing.JButton();
        combo_dirs = new javax.swing.JComboBox();
        btn_reload = new javax.swing.JButton();
        jc_bilinear = new javax.swing.JCheckBox();
        table_scroll = new javax.swing.JScrollPane();
        table_songlist = new javax.swing.JTable();
        txt_filter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        slider_main_vol = new javax.swing.JSlider();
        slider_key_vol = new javax.swing.JSlider();
        slider_bgm_vol = new javax.swing.JSlider();
        lbl_main_vol = new javax.swing.JLabel();
        lbl_key_vol = new javax.swing.JLabel();
        lbl_bgm_vol = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mitem_exit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menu_about = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Open2Jam");

        lbl_title.setFont(new java.awt.Font("Tahoma", 0, 18));
        lbl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title.setText("Title");

        lbl_artist.setFont(new java.awt.Font("Tahoma", 2, 11));
        lbl_artist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_artist.setText("Artist");

        lbl_bpm.setText("content");

        lbl_level.setText("content");

        lbl_notes.setText("content");

        lbl_time.setText("content");

        lbl_genre.setText("content");

        lbl_bpm1.setText("BPM:");

        lbl_genre1.setText("Genre:");

        lbl_level1.setText("Level:");

        lbl_notes1.setText("Notes:");

        lbl_time1.setText("Time:");

        bt_play.setFont(new java.awt.Font("Tahoma", 0, 18));
        bt_play.setText("Play !");
        bt_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_playActionPerformed(evt);
            }
        });

        lbl_cover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_cover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl_cover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_cover.setIconTextGap(0);
        lbl_cover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_coverMouseClicked(evt);
            }
        });

        lbl_keys1.setText("Keys:");

        lbl_keys.setText("content");

        jc_autoplay.setText("Autoplay");

        combo_channelModifier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--None--", "Mirror", "Shuffle", "Random" }));

        lbl_channelModifier.setText("Channel Modifier:");

        combo_visibilityModifier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--None--", "Hidden", "Sudden", "Dark" }));

        lbl_visibilityModifier.setText("Visibility Modifier:");

        lbl_filename.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl_filename.setText("filename");

        table_chartlist.setModel(model_chartlist);
        table_chartlist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table_scroll2.setViewportView(table_chartlist);

        jc_timed_judgment.setSelected(true);
        jc_timed_judgment.setText("Use timed judment");
        jc_timed_judgment.setToolTipText("Like Bemani games");

        javax.swing.GroupLayout panel_infoLayout = new javax.swing.GroupLayout(panel_info);
        panel_info.setLayout(panel_infoLayout);
        panel_infoLayout.setHorizontalGroup(
            panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_infoLayout.createSequentialGroup()
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(table_scroll2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(lbl_title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_infoLayout.createSequentialGroup()
                        .addComponent(lbl_cover, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_infoLayout.createSequentialGroup()
                                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_bpm1)
                                    .addComponent(lbl_genre1)
                                    .addComponent(lbl_level1)
                                    .addComponent(lbl_notes1)
                                    .addComponent(lbl_time1)
                                    .addComponent(lbl_keys1))
                                .addGap(18, 18, 18)
                                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_keys)
                                    .addComponent(lbl_level, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(lbl_notes, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(lbl_time, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(lbl_genre, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(lbl_bpm, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))
                            .addComponent(lbl_filename)))
                    .addComponent(lbl_artist, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addGroup(panel_infoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel_infoLayout.createSequentialGroup()
                                .addComponent(lbl_channelModifier)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combo_channelModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_visibilityModifier))
                            .addGroup(panel_infoLayout.createSequentialGroup()
                                .addComponent(jc_timed_judgment)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jc_autoplay)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_play)
                            .addComponent(combo_visibilityModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panel_infoLayout.setVerticalGroup(
            panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_infoLayout.createSequentialGroup()
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_cover, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_infoLayout.createSequentialGroup()
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_bpm1)
                            .addComponent(lbl_bpm))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_genre)
                            .addComponent(lbl_genre1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_level)
                            .addComponent(lbl_level1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_notes1)
                            .addComponent(lbl_notes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_time)
                            .addComponent(lbl_time1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_keys1)
                            .addComponent(lbl_keys))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_filename)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_artist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table_scroll2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_visibilityModifier)
                    .addComponent(combo_visibilityModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_channelModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_channelModifier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jc_autoplay)
                    .addComponent(bt_play)
                    .addComponent(jc_timed_judgment)))
        );

        rank_group.add(jr_rank_hard);
        jr_rank_hard.setText("Hard");
        jr_rank_hard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jr_rank_hard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jr_rank_hard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr_rank_hardActionPerformed(evt);
            }
        });

        combo_displays.setModel(new javax.swing.DefaultComboBoxModel(display_modes));

        txt_res_height.setText("600");
        txt_res_height.setEnabled(false);

        txt_res_width.setText("800");
        txt_res_width.setEnabled(false);

        jc_vsync.setSelected(true);
        jc_vsync.setText("Use VSync");

        lbl_rank.setText("Rank:");

        lbl_display.setText("Display:");

        jc_custom_size.setFont(new java.awt.Font("Tahoma", 0, 10));
        jc_custom_size.setText("Custom size:");
        jc_custom_size.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                custom_size_clicked(evt);
            }
        });

        rank_group.add(jr_rank_easy);
        jr_rank_easy.setSelected(true);
        jr_rank_easy.setText("Easy");
        jr_rank_easy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jr_rank_easy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jr_rank_easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr_rank_easyActionPerformed(evt);
            }
        });

        lbl_hispeed.setText("Hi-Speed:");

        lbl_res_x.setText("x");

        rank_group.add(jr_rank_normal);
        jr_rank_normal.setText("Normal");
        jr_rank_normal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jr_rank_normal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jr_rank_normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr_rank_normalActionPerformed(evt);
            }
        });

        jc_full_screen.setText("Full screen");

        bt_choose_dir.setText("Choose dir");
        bt_choose_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_choose_dirActionPerformed(evt);
            }
        });

        load_progress.setStringPainted(true);

        js_hispeed.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.5d, 10.0d, 0.5d));

        btn_configuration.setFont(new java.awt.Font("Tahoma", 0, 10));
        btn_configuration.setText("Go!");
        btn_configuration.setMaximumSize(new java.awt.Dimension(20, 20));
        btn_configuration.setMinimumSize(new java.awt.Dimension(20, 20));
        btn_configuration.setPreferredSize(new java.awt.Dimension(20, 20));
        btn_configuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configurationActionPerformed(evt);
            }
        });

        lbl_dirKey.setText("Folders and Keys:");

        lbl_skin_selection.setText("Skin selection:");

        btn_skin.setFont(new java.awt.Font("Tahoma", 0, 10));
        btn_skin.setText("Go!");
        btn_skin.setMaximumSize(new java.awt.Dimension(20, 20));
        btn_skin.setMinimumSize(new java.awt.Dimension(20, 20));
        btn_skin.setPreferredSize(new java.awt.Dimension(20, 20));
        btn_skin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_skinActionPerformed(evt);
            }
        });

        combo_dirs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_dirsActionPerformed(evt);
            }
        });

        btn_reload.setText("Reload");
        btn_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reloadActionPerformed(evt);
            }
        });

        jc_bilinear.setSelected(true);
        jc_bilinear.setText("Bilinear filter");

        javax.swing.GroupLayout panel_settingLayout = new javax.swing.GroupLayout(panel_setting);
        panel_setting.setLayout(panel_settingLayout);
        panel_settingLayout.setHorizontalGroup(
            panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_settingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jc_bilinear)
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addComponent(jr_rank_easy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jr_rank_normal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jr_rank_hard))
                    .addComponent(lbl_rank)
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addComponent(lbl_hispeed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(js_hispeed, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addGap(72, 72, 72))
                    .addComponent(lbl_display)
                    .addComponent(combo_displays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addComponent(bt_choose_dir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(load_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addComponent(jc_custom_size, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_res_width, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_res_x)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_res_height, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_skin_selection)
                            .addComponent(lbl_dirKey, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_configuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_skin, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addComponent(jc_vsync)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jc_full_screen))
                    .addGroup(panel_settingLayout.createSequentialGroup()
                        .addComponent(combo_dirs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reload)))
                .addContainerGap())
        );
        panel_settingLayout.setVerticalGroup(
            panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_settingLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_choose_dir)
                    .addComponent(load_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_rank)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jr_rank_easy)
                    .addComponent(jr_rank_normal)
                    .addComponent(jr_rank_hard))
                .addGap(22, 22, 22)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(js_hispeed, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_hispeed))
                .addGap(18, 18, 18)
                .addComponent(lbl_display)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_displays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_res_width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_res_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_res_x)
                    .addComponent(jc_custom_size))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jc_vsync)
                    .addComponent(jc_full_screen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jc_bilinear)
                .addGap(8, 8, 8)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dirKey)
                    .addComponent(btn_configuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_skin_selection)
                    .addComponent(btn_skin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panel_settingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_dirs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reload))
                .addContainerGap())
        );

        table_songlist.setAutoCreateRowSorter(true);
        table_songlist.setModel(model_songlist);
        table_songlist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table_songlist.getSelectionModel().addListSelectionListener(this);
        table_scroll.setViewportView(table_songlist);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Source");

        slider_main_vol.setPaintLabels(true);
        slider_main_vol.setToolTipText("Main Volume");

        slider_key_vol.setPaintLabels(true);
        slider_key_vol.setToolTipText("Key Volume");
        slider_key_vol.setValue(100);

        slider_bgm_vol.setPaintLabels(true);
        slider_bgm_vol.setToolTipText("BGM Volume");
        slider_bgm_vol.setValue(100);

        lbl_main_vol.setText("Main Volume:");

        lbl_key_vol.setText("Key Volume:");

        lbl_bgm_vol.setText("BGM Volume:");

        jMenu1.setText("File");

        mitem_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mitem_exit.setText("Exit");
        mitem_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_exitActionPerformed(evt);
            }
        });
        jMenu1.add(mitem_exit);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Tools");

        jMenuItem1.setText("OJM Dumper");
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("OJN <-> BMS");
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        menu_about.setText("About");
        menu_about.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_aboutMouseClicked(evt);
            }
        });
        jMenuBar1.add(menu_about);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1))
                        .addComponent(panel_setting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_main_vol)
                            .addComponent(lbl_bgm_vol)
                            .addComponent(lbl_key_vol))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(slider_bgm_vol, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(slider_key_vol, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(slider_main_vol, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_filter, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(table_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(table_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panel_setting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbl_main_vol)
                            .addComponent(slider_main_vol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbl_key_vol)
                            .addComponent(slider_key_vol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbl_bgm_vol)
                            .addComponent(slider_bgm_vol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void custom_size_clicked(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_custom_size_clicked

        if (evt.getStateChange() == ItemEvent.SELECTED){
            combo_displays.setEnabled(false);
            txt_res_width.setEnabled(true);
            lbl_res_x.setEnabled(true);
            txt_res_height.setEnabled(true);
        }else{
            txt_res_width.setEnabled(false);
            lbl_res_x.setEnabled(false);
            txt_res_height.setEnabled(false);
            combo_displays.setEnabled(true);
        }
    }//GEN-LAST:event_custom_size_clicked

    private void bt_choose_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_choose_dirActionPerformed

        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(cwd));
        jfc.setDialogTitle("Choose a directory");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            cwd = jfc.getSelectedFile().getAbsolutePath();
            updateSelection();
        }
    }//GEN-LAST:event_bt_choose_dirActionPerformed

    private void lbl_coverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_coverMouseClicked
        if(selected_header.getCover() == null) return;
        JOptionPane.showMessageDialog(this, null, "Cover",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(selected_header.getCover()));
    }//GEN-LAST:event_lbl_coverMouseClicked

    private void jr_rank_normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr_rank_normalActionPerformed

        rank = 1;
        int sel_row = table_songlist.getSelectedRow();
        if(sel_row >= 0)last_model_idx = table_songlist.convertRowIndexToModel(sel_row);
        model_songlist.setRank(rank);
    }//GEN-LAST:event_jr_rank_normalActionPerformed

    private void jr_rank_easyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr_rank_easyActionPerformed

        rank = 0;
        int sel_row = table_songlist.getSelectedRow();
        if(sel_row >= 0)last_model_idx = table_songlist.convertRowIndexToModel(sel_row);
        model_songlist.setRank(rank);
    }//GEN-LAST:event_jr_rank_easyActionPerformed

    private void jr_rank_hardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr_rank_hardActionPerformed

        rank = 2;
        int sel_row = table_songlist.getSelectedRow();
        if(sel_row >= 0)last_model_idx = table_songlist.convertRowIndexToModel(sel_row);
        model_songlist.setRank(rank);
    }//GEN-LAST:event_jr_rank_hardActionPerformed

    private void bt_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_playActionPerformed

        if(selected_header != null)
	{
	    final double hispeed = (Double) js_hispeed.getValue();

	    final DisplayMode dm;
	    if(jc_custom_size.isSelected()){ // custom size selected
		int w,h;
		try{
		    w = Integer.parseInt(txt_res_width.getText());
		    h = Integer.parseInt(txt_res_height.getText());
		}catch(Exception e){
		    JOptionPane.showMessageDialog(this, "Invalid value on custom size", "Error", JOptionPane.WARNING_MESSAGE);
		    return;
		}
		dm = new DisplayMode(w,h);
	    }else{
		dm = (DisplayMode) combo_displays.getSelectedItem();
	    }
	    final boolean vsync = jc_vsync.isSelected();
	    final boolean fs = jc_full_screen.isSelected();
            final boolean bilinear = jc_bilinear.isSelected();

	    final boolean autoplay = jc_autoplay.isSelected();

            final boolean judgment = jc_timed_judgment.isSelected();

	    final int channelModifier = combo_channelModifier.getSelectedIndex();
            final int visibilityModifier = combo_visibilityModifier.getSelectedIndex();

            final int mainVol = slider_main_vol.getValue();
            final int keyVol = slider_key_vol.getValue();
            final int bgmVol = slider_bgm_vol.getValue();

            if(!dm.isFullscreenCapable() && fs)
            {
                String str = "This monitor can't support the selected resolution.\n"
                        + "Do you want to play it in windowed mode?";
                if(JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    Render r = null;
                    if(judgment)
                        r = new BeatmaniaRender(selected_header, hispeed, autoplay, channelModifier, visibilityModifier, mainVol, keyVol, bgmVol);
                    else
                        r = new O2jamRender(selected_header, hispeed, autoplay, channelModifier, visibilityModifier, mainVol, keyVol, bgmVol);
                    r.setDisplay(dm, vsync, false, bilinear);
                    r.startRendering();
                }
            }
            else
            {
                Render r = null;
                if(judgment)
                    r = new BeatmaniaRender(selected_header, hispeed, autoplay, channelModifier, visibilityModifier, mainVol, keyVol, bgmVol);
                else
                    r = new O2jamRender(selected_header, hispeed, autoplay, channelModifier, visibilityModifier, mainVol, keyVol, bgmVol);
                r.setDisplay(dm, vsync, fs, bilinear);
                r.startRendering();
            }
	}
    }//GEN-LAST:event_bt_playActionPerformed

    private void btn_configurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_configurationActionPerformed
	cfg_window.setVisible(true);
    }//GEN-LAST:event_btn_configurationActionPerformed

    private void mitem_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_exitActionPerformed
        System.exit(0); //TODO Not a good idea XD
    }//GEN-LAST:event_mitem_exitActionPerformed

    private void menu_aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_aboutMouseClicked
        String about = "Open2Jam\n"
                + "Main programmer: ChaosFox\n"
                + "Main code destroyer: CdK"    ;
        JOptionPane.showMessageDialog(this, about, "About", JOptionPane.ERROR_MESSAGE, null);
    }//GEN-LAST:event_menu_aboutMouseClicked

    private void btn_skinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_skinActionPerformed
        skin_window.setVisible(true);
    }//GEN-LAST:event_btn_skinActionPerformed

    private void combo_dirsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_dirsActionPerformed
        if(dir_list.isEmpty()) return;
        if(combo_dirs.getSelectedIndex()<0) return;
        String s = dir_list.get(combo_dirs.getSelectedIndex());
        cwd = s;
        File cache = new File(stringToCRC32(cwd)); //if exist do nothing
        if(cache.exists())
            loadCache(cwd);
        else
            updateSelection();
    }//GEN-LAST:event_combo_dirsActionPerformed

    private void btn_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reloadActionPerformed
        updateSelection();
    }//GEN-LAST:event_btn_reloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_choose_dir;
    private javax.swing.JButton bt_play;
    private javax.swing.JButton btn_configuration;
    private javax.swing.JButton btn_reload;
    private javax.swing.JButton btn_skin;
    private javax.swing.JComboBox combo_channelModifier;
    private javax.swing.JComboBox combo_dirs;
    private javax.swing.JComboBox combo_displays;
    private javax.swing.JComboBox combo_visibilityModifier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JCheckBox jc_autoplay;
    private javax.swing.JCheckBox jc_bilinear;
    private javax.swing.JCheckBox jc_custom_size;
    private javax.swing.JCheckBox jc_full_screen;
    private javax.swing.JCheckBox jc_timed_judgment;
    private javax.swing.JCheckBox jc_vsync;
    private javax.swing.JRadioButton jr_rank_easy;
    private javax.swing.JRadioButton jr_rank_hard;
    private javax.swing.JRadioButton jr_rank_normal;
    private javax.swing.JSpinner js_hispeed;
    private javax.swing.JLabel lbl_artist;
    private javax.swing.JLabel lbl_bgm_vol;
    private javax.swing.JLabel lbl_bpm;
    private javax.swing.JLabel lbl_bpm1;
    private javax.swing.JLabel lbl_channelModifier;
    private javax.swing.JLabel lbl_cover;
    private javax.swing.JLabel lbl_dirKey;
    private javax.swing.JLabel lbl_display;
    private javax.swing.JLabel lbl_filename;
    private javax.swing.JLabel lbl_genre;
    private javax.swing.JLabel lbl_genre1;
    private javax.swing.JLabel lbl_hispeed;
    private javax.swing.JLabel lbl_key_vol;
    private javax.swing.JLabel lbl_keys;
    private javax.swing.JLabel lbl_keys1;
    private javax.swing.JLabel lbl_level;
    private javax.swing.JLabel lbl_level1;
    private javax.swing.JLabel lbl_main_vol;
    private javax.swing.JLabel lbl_notes;
    private javax.swing.JLabel lbl_notes1;
    private javax.swing.JLabel lbl_rank;
    private javax.swing.JLabel lbl_res_x;
    private javax.swing.JLabel lbl_skin_selection;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JLabel lbl_time1;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_visibilityModifier;
    private javax.swing.JProgressBar load_progress;
    private javax.swing.JMenu menu_about;
    private javax.swing.JMenuItem mitem_exit;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel panel_setting;
    private javax.swing.ButtonGroup rank_group;
    private javax.swing.JSlider slider_bgm_vol;
    private javax.swing.JSlider slider_key_vol;
    private javax.swing.JSlider slider_main_vol;
    private javax.swing.JTable table_chartlist;
    private javax.swing.JScrollPane table_scroll;
    private javax.swing.JScrollPane table_scroll2;
    private javax.swing.JTable table_songlist;
    private javax.swing.JTextField txt_filter;
    private javax.swing.JTextField txt_res_height;
    private javax.swing.JTextField txt_res_width;
    // End of variables declaration//GEN-END:variables

    private void initLogic() {
        /* TODO A table with the dirs in the config.obj and change the cwd and the way it loads right now
         * also, it would be nice if we can construct some kind of song's db
         */
        dir_list = Config.get().getDirsList();
        if(dir_list.isEmpty()) cwd = System.getProperty("user.dir");
        else                   cwd = dir_list.get(0);

        ArrayList<DisplayMode> list = new ArrayList<DisplayMode>();
        try {
            display_modes = Display.getAvailableDisplayModes();
            list.addAll(Arrays.asList(display_modes));
        } catch (LWJGLException ex) {
            logger.log(Level.WARNING, "Could not get the display modes !! {0}", ex.getMessage());
        }

        class DisplayComparator implements Comparator<DisplayMode> {
            public int compare(DisplayMode dm1, DisplayMode dm2) {
                int width1 = dm1.getWidth();
                int width2 = dm2.getWidth();
                int height1 = dm1.getHeight();
                int height2 = dm2.getHeight();
                int depth1 = dm1.getBitsPerPixel();
                int depth2 = dm2.getBitsPerPixel();
                int hz1 = dm1.getFrequency();
                int hz2 = dm2.getFrequency();

                if(depth1 == depth2)
                {
                    if(width1 == width2)
                    {
                        if(height1 > height2) return 1;
                        if(height1 < height2) return -1;
                        if(height1 == height2)
                        {
                            if(hz1 > hz2) return -1;
                            if(hz1 < hz2) return 1;
                            if(hz1 == hz2) return 0;
                        }
                    }
                    else if (width1 > width2) return 1;
                    else if (width1 < width2) return -1;
                }
                else if(depth1 > depth2) return -1;

                return 1;
            }
        }

        Collections.sort(list, new DisplayComparator());

        display_modes = list.toArray(new DisplayMode[list.size()]);

        model_songlist = new ChartListTableModel();
        model_chartlist = new ChartTableModel();

        loadCache(cwd);
    }

    //TODO Move this function to its own class, with other ones xD
    public static String stringToCRC32(String s)
    {
         //let's make a crc32 hash for the cache name
        CRC32 cs = new CRC32();
        cs.reset();

        byte[] d = s.getBytes();
        cs.update(d, 0, d.length);

        return "cache_"+Long.toHexString(cs.getValue()).toUpperCase()+".obj";
    }

    private void loadCache(String dir)
    {
        File cache = new File(stringToCRC32(dir));
        if(cache.exists()){
            this.setTitle("Open2Jam - "+dir);
            try {
                GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(cache));
                ObjectInputStream obj = new ObjectInputStream(gzip);
                @SuppressWarnings("unchecked") // yes, I'm sure its a list of chartlist
                List<ChartList> l = (List<ChartList>) obj.readObject();
                model_songlist.clear();
                for(ChartList c : l)model_songlist.addRow(c);
                obj.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "{0}", ex);
            } catch (ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "{0}", ex);
            }
        }
    }

    private void updateSelection() {
        this.setTitle("Open2Jam - "+cwd);
        bt_choose_dir.setEnabled(false);
        btn_reload.setEnabled(false);
        combo_dirs.setEnabled(false);
        txt_filter.setVisible(false);
        table_songlist.setEnabled(false);
        load_progress.setValue(0);
        load_progress.setVisible(true);
        task = new ChartModelLoader(model_songlist, new File(cwd));
        task.addPropertyChangeListener(this);
        task.execute();
    }

    private void loadDirlist()
    {
        DefaultComboBoxModel theModel = (DefaultComboBoxModel)combo_dirs.getModel();
        theModel.removeAllElements();
        if(dir_list.isEmpty()) return;
        for(int i=0; i<dir_list.size(); i++)
        {
            String s = dir_list.get(i);
            
            if(s.contains("\\"))     s =".."+s.substring(s.lastIndexOf("\\"));
            else if(s.contains("/")) s =".."+s.substring(s.lastIndexOf("/"));
            
            if(s.length()>18)        s = ".."+s.substring(s.length()-16);

            combo_dirs.addItem(s);
        }
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        if("progress".equals(evt.getPropertyName()))
        {
            int i = (Integer) evt.getNewValue();
            load_progress.setValue(i);
            if(i == 100)
            {
                bt_choose_dir.setEnabled(true);
                combo_dirs.setEnabled(true);
                btn_reload.setEnabled(true);
                load_progress.setVisible(false);
                txt_filter.setVisible(true);
                table_songlist.setEnabled(true);
            }
        }
    }

    public void updateFilter() {
        try {
            if(txt_filter.getText().length() == 0)table_sorter.setRowFilter(null);
            else table_sorter.setRowFilter(RowFilter.regexFilter("(?i)"+txt_filter.getText()));
        } catch (java.util.regex.PatternSyntaxException ex) {
            return;
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        int i = table_songlist.getSelectedRow();
        if(i < 0 && last_model_idx >= 0){
            i = last_model_idx;
            int i_view = table_songlist.convertRowIndexToView(i);
            table_songlist.getSelectionModel().setSelectionInterval(0, i_view);
            table_scroll.getVerticalScrollBar().setValue(table_songlist.getCellRect(i_view, 0, false).y);
        }else{
            i = table_songlist.convertRowIndexToModel(i);
        }
        selected_chart = model_songlist.getRow(i);
        if(selected_chart.size() > rank)selected_header = selected_chart.get(rank);
        if(selected_chart != model_chartlist.getChartList()){
            model_chartlist.clear();
            model_chartlist.setChartList(selected_chart);
        }
        if(selected_chart.size()-1 < rank)
            table_chartlist.getSelectionModel().setSelectionInterval(0, 0);
        else
            table_chartlist.getSelectionModel().setSelectionInterval(0, rank);
        updateInfo();
    }

    private void updateInfo()
    {
        if(selected_header == null)return;
        if(!selected_header.getSource().exists()) {JOptionPane.showMessageDialog(this, "Doesn't Exist"); return;}
        lbl_artist.setText(resizeString(selected_header.getArtist(), 40));
        lbl_title.setText(resizeString(selected_header.getTitle(), 30));
        lbl_filename.setText(resizeString(selected_header.getSource().getName(), 30));
        lbl_genre.setText(resizeString(selected_header.getGenre(), 30));
        lbl_level.setText(selected_header.getLevel()+"");
        lbl_bpm.setText(Float.toString((float)selected_header.getBPM()*100/100));
        lbl_notes.setText(selected_header.getNoteCount()+"");
	lbl_keys.setText(selected_header.getKeys()+"");
        int d = selected_header.getDuration();
        lbl_time.setText((d/60)+":"+(d%60 < 10 ? "0"+(d%60) : (d%60)));

        BufferedImage i = selected_header.getCover();

        if(i != null)
        lbl_cover.setIcon(new ImageIcon(i.getScaledInstance(
                lbl_cover.getWidth(),
                lbl_cover.getHeight(),
                BufferedImage.SCALE_SMOOTH
                )));
        else
            lbl_cover.setIcon(null);
    }

    private String resizeString(String string, int size)
    {
        if(string == null)return "";
        if(string.length() > size)
            string = string.substring(0, size)+"...";
        return string;
    }
}
